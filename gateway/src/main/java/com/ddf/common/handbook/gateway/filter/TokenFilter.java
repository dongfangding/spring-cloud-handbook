package com.ddf.common.handbook.gateway.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.ddf.boot.common.api.exception.BaseCallbackCode;
import com.ddf.boot.common.api.exception.BaseException;
import com.ddf.boot.common.api.exception.BusinessException;
import com.ddf.boot.common.api.exception.UnauthorizedException;
import com.ddf.boot.common.api.model.authentication.AuthenticateCheckResult;
import com.ddf.boot.common.api.model.authentication.UserClaim;
import com.ddf.boot.common.api.model.common.request.RequestHeaderEnum;
import com.ddf.boot.common.api.model.common.response.response.ResponseData;
import com.ddf.boot.common.api.util.JsonUtil;
import com.ddf.boot.common.core.authentication.AuthenticationProperties;
import com.ddf.boot.common.core.authentication.TokenUtil;
import com.ddf.boot.common.core.helper.EnvironmentHelper;
import com.ddf.boot.common.core.util.GlobalAntMatcher;
import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.common.handbook.gateway.infrastructure.config.GatewayAuthProperties;
import com.ddf.common.handbook.gateway.infrastructure.exception.GatewayExceptionCode;
//import com.ddf.common.handbook.user.constants.UserRedisKey;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @since 2023/06/23 10:21
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenFilter implements GlobalFilter {

    private final GatewayAuthProperties gatewayAuthProperties;
    private final AuthenticationProperties authenticationProperties;
    private final EnvironmentHelper environmentHelper;
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();
        final HttpHeaders headers = request.getHeaders();
        final String uri = request.getURI().getPath();
        final List<String> whiteUrlList = gatewayAuthProperties.getWhiteUrlList();
        final String token = headers.getFirst(authenticationProperties.getTokenHeaderName());
        // 非白名单
        if (!GlobalAntMatcher.match(whiteUrlList, uri)) {
            try {
                final UserClaim userClaim = checkAndParseAuthInfo(request, token);
            } catch (BaseException e) {
                return responseErrorJson(exchange.getResponse(), e.getBaseCallbackCode());
            }
        }
        // 重新构建请求，添加请求头，传递到下游服务
        ServerHttpRequest mutableReq = request.mutate()
                .header(RequestHeaderEnum.SIGN.getName(), headers.getFirst(RequestHeaderEnum.SIGN.getName()))
                .header(RequestHeaderEnum.OS.getName(), headers.getFirst(RequestHeaderEnum.OS.getName()))
                .header(RequestHeaderEnum.IMEI.getName(), headers.getFirst(RequestHeaderEnum.IMEI.getName()))
                .header(RequestHeaderEnum.NONCE.getName(), headers.getFirst(RequestHeaderEnum.NONCE.getName()))
                .header(RequestHeaderEnum.VERSION.getName(), headers.getFirst(RequestHeaderEnum.VERSION.getName()))
                .header(RequestHeaderEnum.LONGITUDE.getName(), headers.getFirst(RequestHeaderEnum.LONGITUDE.getName()))
                .header(RequestHeaderEnum.LATITUDE.getName(), headers.getFirst(RequestHeaderEnum.LATITUDE.getName()))
                .header(RequestHeaderEnum.CLIENT_IP_FROM_GATEWAY.getName(), getClientIp(request))
                .build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
    }

    /**
     * 返回错误json信息
     *
     * @param response
     * @param bizCode
     * @return
     */
    private Mono<Void> responseErrorJson(ServerHttpResponse response, BaseCallbackCode bizCode) {
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String result = JSONUtil.toJsonStr(ResponseData.failure(bizCode));
        DataBuffer buffer = response.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(buffer));
    }


    /**
     * 获取客户端请求ip
     *
     * @param request
     * @return
     */
    public static String getClientIp(ServerHttpRequest request) {
        final HttpHeaders headers = request.getHeaders();
        String ip = headers.getFirst("X-Forwarded-For");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip) && ip.contains(",")) {
            ip = ip.split(",")[0];
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
        }
        return ip;
    }



    /**
     * 校验并转换用户信息
     *
     * @param request
     * @param tokenHeader
     * @return
     */
    private UserClaim checkAndParseAuthInfo(ServerHttpRequest request,  String tokenHeader) {
        UserClaim tokenUserClaim;
        String tokenPrefix = authenticationProperties.getTokenPrefix();
        if (tokenHeader == null || !tokenHeader.startsWith(tokenPrefix)) {
            throw new UnauthorizedException("token格式不合法！");
        }
        String token = tokenHeader.split(tokenPrefix)[1];

        UserClaim storeUser;
        if (authenticationProperties.isMock() && !environmentHelper.isProdProfile() &&
                CollUtil.isNotEmpty(authenticationProperties.getMockUserIdList()) && authenticationProperties.getMockUserIdList().contains(token)) {
            tokenUserClaim = UserClaim.mockUser(token);
            storeUser = getUserClaimFromToken(tokenUserClaim);
        } else {
            AuthenticateCheckResult authenticateCheckResult = TokenUtil.checkToken(token);
            tokenUserClaim = authenticateCheckResult.getUserClaim();
            // 额外业务token校验规则
            storeUser = customizeCheck(request, authenticateCheckResult);
        }
        return storeUser;
    }


    /**
     * 根据token的用户信息重新获取最新的用户信息
     *
     * @param tokenUserClaim
     * @return
     */
    private UserClaim getUserClaimFromToken(UserClaim tokenUserClaim) {
        final String userJsonStr = stringRedisTemplate.opsForValue()
                .get("");
        if (StringUtils.isBlank(userJsonStr)) {
            throw new BusinessException(GatewayExceptionCode.USER_INFO_EXPIRED_OR_NOT_EXIST);
        }
        return JsonUtil.toBean(userJsonStr, UserClaim.class);
    }

    private UserClaim customizeCheck(ServerHttpRequest request, AuthenticateCheckResult authenticateCheckResult) {
        final UserClaim tokenUserClaim = authenticateCheckResult.getUserClaim();
        PreconditionUtil.checkArgument(Objects.nonNull(tokenUserClaim), "解析用户为空!");
        PreconditionUtil.checkArgument(!StringUtils.isAnyBlank(tokenUserClaim.getUsername(), tokenUserClaim.getCredit()),
                "用户关键信息缺失！");
        // credit校验
        final String credit = StringUtils.defaultIfBlank(request.getHeaders().getFirst(authenticationProperties.getCreditHeaderName()),
                request.getHeaders().getFirst("User-Agent"));
        if (Objects.nonNull(tokenUserClaim.getCredit()) && !Objects.equals(tokenUserClaim.getCredit(), credit)) {
            log.error("当前请求credit和token不匹配， 当前: {}, token: {}", credit, tokenUserClaim.getCredit());
            throw new UnauthorizedException("登录环境变更，需要重新登录！");
        }
        // 获取最新用户信息
        UserClaim storeUser = getUserClaimFromToken(tokenUserClaim);
        PreconditionUtil.checkArgument(!storeUser.isDisabled(), new UnauthorizedException("用户已被关进小黑屋了~"));
        return storeUser;
    }
}

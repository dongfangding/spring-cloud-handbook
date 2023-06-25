package com.ddf.common.handbook.user.api;

import com.ddf.boot.common.api.model.common.response.response.ResponseData;
import com.ddf.common.handbook.user.constants.UserConst;
import com.ddf.common.handbook.user.request.base.EmailRegistryRequest;
import com.ddf.common.handbook.user.request.base.EmailRegistryValidateRequest;
import com.ddf.common.handbook.user.request.base.SearchUserRequest;
import com.ddf.common.handbook.user.response.base.EmailRegistryValidateResponse;
import com.ddf.common.handbook.user.response.base.UserInfoDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>用户相关app接口</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/04/09 23:15
 */
@FeignClient(name = UserConst.SERVICE_NAME, path = UserConst.CONTEXT_PATH + "/userInfo", contextId = "appUserInfoFacade")
public interface AppUserInfoFacade {

    /**
     * 校验注册信息是否可用
     *
     * @param request
     * @return
     */
    @PostMapping("checkRegistryInfo")
    ResponseData<EmailRegistryValidateResponse> checkRegistryInfo(@RequestBody @Validated EmailRegistryValidateRequest request);

    /**
     * 邮箱注册
     *
     * @param request
     * @return
     */
    @PostMapping("registry")
    ResponseData<Boolean> registry(@RequestBody @Validated EmailRegistryRequest request);

    /**
     * 查询用户
     *
     * @param request
     * @return
     */
    @GetMapping("searchUser")
    ResponseData<List<UserInfoDTO>> searchUser(@Validated SearchUserRequest request);

}

package com.ddf.common.handbook.aggregate.core.client;

import com.ddf.boot.common.api.model.common.response.response.ResponseData;
import com.ddf.common.handbook.user.api.AppUserInfoFacade;
import com.ddf.common.handbook.user.constants.UserConst;
import com.ddf.common.handbook.user.request.base.EmailRegistryRequest;
import com.ddf.common.handbook.user.request.base.EmailRegistryValidateRequest;
import com.ddf.common.handbook.user.request.base.SearchUserRequest;
import com.ddf.common.handbook.user.response.base.EmailRegistryValidateResponse;
import com.ddf.common.handbook.user.response.base.UserInfoDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2023/03/08 17:37
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
@FeignClient(name = UserConst.SERVICE_NAME, path = "/userInfo", contextId = "appUserInfoFacade")
public class AppUserInfoClient {

    private final AppUserInfoFacade appUserInfoFacade;

    /**
     * 校验注册信息是否可用
     *
     * @param request
     * @return
     */
    public EmailRegistryValidateResponse checkRegistryInfo(EmailRegistryValidateRequest request) {
        appUserInfoFacade.checkRegistryInfo(request).requiredSuccess();
        return appUserInfoFacade.checkRegistryInfo(request).requiredSuccess();
    }

    /**
     * 邮箱注册
     *
     * @param request
     * @return
     */
    public Boolean registry(@RequestBody @Validated EmailRegistryRequest request) {
        return appUserInfoFacade.registry(request).requiredSuccess();
    }

    /**
     * 查询用户
     *
     * @param request
     * @return
     */
    public List<UserInfoDTO> searchUser(@Validated SearchUserRequest request) {
        return appUserInfoFacade.searchUser(request).requiredSuccess();
    }
}

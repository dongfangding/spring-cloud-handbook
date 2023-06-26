package com.ddf.common.handbook.aggregate.core.controller;

import com.ddf.boot.common.api.model.common.response.response.ResponseData;
import com.ddf.common.handbook.aggregate.core.biz.UserServiceBiz;
import com.ddf.common.handbook.aggregate.core.client.AppUserInfoClient;
import com.ddf.common.handbook.user.request.base.EmailRegistryValidateRequest;
import com.ddf.common.handbook.user.response.base.EmailRegistryValidateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2023/03/08 18:06
 */
@RestController
@RequestMapping("user")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class UserController {

    private final UserServiceBiz userServiceBiz;

    /**
     * 校验注册信息是否可用
     *
     * @param request
     * @return
     */
    @PostMapping("checkRegistryInfo")
    public ResponseData<EmailRegistryValidateResponse> checkRegistryInfo(@RequestBody @Validated EmailRegistryValidateRequest request) {
        return ResponseData.success(userServiceBiz.checkRegistryInfo(request));
    }
}

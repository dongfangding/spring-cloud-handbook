package com.ddf.common.handbook.aggregate.core.biz;

import com.ddf.common.handbook.aggregate.core.client.AppUserInfoClient;
import com.ddf.common.handbook.user.request.base.EmailRegistryValidateRequest;
import com.ddf.common.handbook.user.response.base.EmailRegistryValidateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @since 2023/06/26 09:26
 */
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
@Service
public class UserServiceBiz {

    private final AppUserInfoClient appUserInfoClient;

    public EmailRegistryValidateResponse checkRegistryInfo(EmailRegistryValidateRequest request) {
        // 检测注册信息是否可用
        return appUserInfoClient.checkRegistryInfo(request);
        // 注册 --> user
        // 订单 --> order
    }

}

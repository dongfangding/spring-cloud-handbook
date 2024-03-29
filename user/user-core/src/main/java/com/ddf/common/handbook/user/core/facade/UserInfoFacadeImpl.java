package com.ddf.common.handbook.user.core.facade;

import com.ddf.boot.common.api.model.common.response.response.ResponseData;
import com.ddf.common.handbook.user.api.AppUserInfoFacade;
import com.ddf.common.handbook.user.request.base.EmailRegistryRequest;
import com.ddf.common.handbook.user.request.base.EmailRegistryValidateRequest;
import com.ddf.common.handbook.user.request.base.SearchUserRequest;
import com.ddf.common.handbook.user.response.base.EmailRegistryValidateResponse;
import com.ddf.common.handbook.user.response.base.UserInfoDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>用户信息对外接口层</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/04/10 11:11
 */
@RestController
@RequestMapping("/userInfo")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserInfoFacadeImpl implements AppUserInfoFacade {

    /**
     * 校验注册信息是否可用
     *
     * @param request
     * @return
     */
    @Override
    public ResponseData<EmailRegistryValidateResponse> checkRegistryInfo(EmailRegistryValidateRequest request) {
        log.info("用户接口-校验注册信息被调用");
        return ResponseData.success(new EmailRegistryValidateResponse());
    }

    /**
     * 邮箱注册
     *
     * @param request
     * @return
     */
    @Override
    public ResponseData<Boolean> registry(EmailRegistryRequest request) {
        return null;
    }

    /**
     * 查询用户
     *
     * @param request
     * @return
     */
    @Override
    public ResponseData<List<UserInfoDTO>> searchUser(SearchUserRequest request) {
        return null;
    }
}

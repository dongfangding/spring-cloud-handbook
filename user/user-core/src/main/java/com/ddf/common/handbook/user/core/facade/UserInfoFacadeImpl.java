package com.ddf.common.handbook.user.core.facade;

import com.ddf.common.handbook.user.api.AppUserInfoFacade;
import com.ddf.common.handbook.user.request.base.EmailRegistryRequest;
import com.ddf.common.handbook.user.request.base.EmailRegistryValidateRequest;
import com.ddf.common.handbook.user.request.base.SearchUserRequest;
import com.ddf.common.handbook.user.response.base.EmailRegistryValidateResponse;
import com.ddf.common.handbook.user.response.base.UserInfoDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
public class UserInfoFacadeImpl implements AppUserInfoFacade {

    /**
     * 校验注册信息是否可用
     *
     * @param request
     * @return
     */
    @Override
    public EmailRegistryValidateResponse checkRegistryInfo(EmailRegistryValidateRequest request) {
        return null;
    }

    /**
     * 邮箱注册
     *
     * @param request
     * @return
     */
    @Override
    public Boolean registry(EmailRegistryRequest request) {
        return null;
    }

    /**
     * 查询用户
     *
     * @param request
     * @return
     */
    @Override
    public List<UserInfoDTO> searchUser(SearchUserRequest request) {
        return null;
    }
}

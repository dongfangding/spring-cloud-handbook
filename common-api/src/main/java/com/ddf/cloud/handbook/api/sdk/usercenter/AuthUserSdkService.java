package com.ddf.cloud.handbook.api.sdk.usercenter;

import com.ddf.cloud.handbook.api.constant.ApiConstant;
import com.ddf.cloud.handbook.api.model.usercenter.AuthUser;
import com.ddf.cloud.handbook.core.response.ResponseData;
import feign.Request;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2020/06/29 16:26
 */
@FeignClient(name = ApiConstant.USER_CENTER_SERVICE_NAME, path = ApiConstant.USER_CENTER_SERVER_CONTEXT, contextId = "authUserService")
public interface AuthUserSdkService {

    /**
     * 查询全部用户列表
     * @param options 控制超时参数
     * @return
     */
    @GetMapping("/user/listAll")
    ResponseData<List<AuthUser>> listAll(Request.Options options);

    /**
     * 查询全部用户列表
     * @return
     */
    @GetMapping("/user/listAll")
    ResponseData<List<AuthUser>> listAll();

}

package com.ddf.common.handbook.gateway.infrastructure.exception;

import com.ddf.boot.common.api.exception.BaseCallbackCode;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @since 2023/06/23 21:55
 */
public enum GatewayExceptionCode implements BaseCallbackCode {

    ILLEGAL_TOKEN("ILLEGAL_TOKEN", "token格式不合法"),
    USER_INFO_EXPIRED_OR_NOT_EXIST("USER_INFO_EXPIRED_OR_NOT_EXIST", "用户信息已失效，请重新登录"),
    USER_ENVIRONMENT_CHANGED("USER_ENVIRONMENT_CHANGED", "用户环境变更，请重新登陆"),
    USER_IN_BLACK_LIST("USER_IN_BLACK_LIST", "用户已被关进小黑屋")


    ;

    private final String code;

    private final String description;

    private final String bizMessage;

    GatewayExceptionCode(String code, String description) {
        this.code = code;
        this.description = description;
        this.bizMessage = description;
    }

    GatewayExceptionCode(String code, String description, String bizMessage) {
        this.code = code;
        this.description = description;
        this.bizMessage = bizMessage;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getBizMessage() {
        return bizMessage;
    }
}

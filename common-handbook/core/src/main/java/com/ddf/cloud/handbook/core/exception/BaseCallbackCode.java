package com.ddf.cloud.handbook.core.exception;

/**
 * <p>异常消息代码统一接口</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2020/06/17 14:58
 */
public interface BaseCallbackCode {

    /**
     * 响应状态码
     *
     * @return
     */
    String getCode();

    /**
     * 响应消息
     *
     * @return
     */
    String getDescription();
}

package com.ddf.common.handbook.user.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * <p>feign client 配置类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/04/07 09:48
 */
@Configuration
@EnableFeignClients("com.ddf.common.handbook.user.api")
public class FeignAutoConfiguration {
}

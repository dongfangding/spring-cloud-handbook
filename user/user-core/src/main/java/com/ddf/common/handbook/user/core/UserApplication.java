package com.ddf.common.handbook.user.core;

import com.ddf.boot.common.core.logaccess.EnableLogAspect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/04/10 22:22
 */
@SpringCloudApplication
@MapperScan(basePackages = {"com.ddf.common.handbook.user.core.infrastructure.mapper"})
@EnableLogAspect(slowTime = 3000)
//@EnableRepeatable(globalValidator = RedisRepeatableValidator.BEAN_NAME)
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
}

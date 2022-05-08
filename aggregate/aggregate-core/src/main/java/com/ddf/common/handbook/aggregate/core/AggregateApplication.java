package com.ddf.common.handbook.aggregate.core;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddf.boot.common.core.logaccess.EnableLogAspect;
import com.ddf.boot.common.limit.repeatable.annotation.EnableRepeatable;
import com.ddf.boot.common.limit.repeatable.validator.RedisRepeatableValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/04/10 22:22
 */
@SpringCloudApplication
@EnableLogAspect(slowTime = 3000)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@EnableRepeatable(globalValidator = RedisRepeatableValidator.BEAN_NAME)
public class AggregateApplication {
    public static void main(String[] args) {
        SpringApplication.run(AggregateApplication.class);
    }
}

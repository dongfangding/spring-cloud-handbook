package com.ddf.common.handbook.aggregate.core;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddf.boot.common.limit.repeatable.annotation.EnableRepeatable;
import com.ddf.boot.common.limit.repeatable.validator.RedisRepeatableValidator;
import com.ddf.boot.common.mvc.logaccess.EnableLogAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @since 2022/04/10 22:22
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@EnableRepeatable(globalValidator = RedisRepeatableValidator.BEAN_NAME)
@EnableLogAspect
public class AggregateApplication {
    public static void main(String[] args) {
        SpringApplication.run(AggregateApplication.class);
    }
}

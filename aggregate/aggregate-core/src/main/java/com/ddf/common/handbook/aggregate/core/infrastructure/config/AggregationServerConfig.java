package com.ddf.common.handbook.aggregate.core.infrastructure.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2023/03/08 18:15
 */
@Configuration
public class AggregationServerConfig {

    /**
     * 负载均衡，服务调用
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

package com.ddf.common.handbook.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @since  2023/06/23 09:41
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class);
    }
}

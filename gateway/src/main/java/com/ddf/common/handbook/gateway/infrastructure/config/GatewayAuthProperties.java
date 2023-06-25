package com.ddf.common.handbook.gateway.infrastructure.config;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @since 2023/06/23 10:02
 */
@RefreshScope
@Component
@Data
@ConfigurationProperties(prefix = "customize.auth")
public class GatewayAuthProperties {

    /**
     * url白名单，不需要校验用户信息的
     */
    private List<String> whiteUrlList;

    /**
     * ip黑名单
     */
    private List<String> blackIpList;

    /**
     * 用户黑名单
     */
    private List<String> blackUserIdList;

    /**
     * 设备黑名单
     */
    private List<String> blackImeiList;
}

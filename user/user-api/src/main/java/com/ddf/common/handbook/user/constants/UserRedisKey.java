package com.ddf.common.handbook.user.constants;

import com.ddf.boot.common.api.constraint.redis.RedisKeyConstraint;
import com.ddf.boot.common.api.constraint.redis.RedisShardingRule;
import com.ddf.boot.common.api.enums.RedisKeyTypeEnum;
import java.time.Duration;

/**
 * <p>用户模块redis key定义</p >
 *
 * @author Snowball
 * @version 1.0
 * @since 2023/06/23 21:07
 */
public enum UserRedisKey implements RedisKeyConstraint {

    /**
     * 用户信息
     * %s uid
     */
    USER_INFO("user:user_info:%s", Duration.ofDays(3), RedisKeyTypeEnum.STRING)

    ;
    private final String template;
    private Duration ttl;

    private final RedisKeyTypeEnum redisKeyType;

    private RedisShardingRule redisShardingRule;

    UserRedisKey(String template, RedisKeyTypeEnum keyType) {
        this.template = template;
        this.ttl = Duration.ofSeconds(-1);
        this.redisKeyType = keyType;
    }

    UserRedisKey(String template, Duration ttl, RedisKeyTypeEnum keyType) {
        this.template = template;
        this.ttl = ttl;
        this.redisKeyType = keyType;
    }

    UserRedisKey(String template, RedisKeyTypeEnum keyType, RedisShardingRule redisShardingRule) {
        this.template = template;
        this.redisKeyType = keyType;
        this.redisShardingRule = redisShardingRule;
    }

    @Override
    public String getTemplate() {
        return template;
    }

    @Override
    public Duration getTtl() {
        return ttl;
    }

    @Override
    public RedisKeyTypeEnum getRedisKeyType() {
        return redisKeyType;
    }

    @Override
    public <S, M> RedisShardingRule<S, M> getRedisShardingRule() {
        return redisShardingRule;
    }
}

package com.cn.config;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfiguration {

    @Bean
    public JedisPoolConfig jedisPoolConfig(@Value("${jedis.maxTotal}") int maxActive,
                                           @Value("${jedis.maxIdle}") int maxIdle,
                                           @Value("${jedis.minIdle}") int minIdle,
                                           @Value("${jedis.maxWaitMillis}") long maxWaitMillis,
                                           @Value("${jedis.testOnBorrow}") boolean testOnBorrow) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);

        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool(@Value("${jedis.host}") String host,
                               @Value("${jedis.password}") String password,
                               @Value("${jedis.port}") int port,
                               @Value("${jedis.timeout}") int timeout, JedisPoolConfig jedisPoolConfig) {
        if(StringUtils.isNotEmpty(password)) {
            return new JedisPool(jedisPoolConfig, host, port, timeout, password);
        }
        return new JedisPool(jedisPoolConfig, host, port, timeout);
    }

}

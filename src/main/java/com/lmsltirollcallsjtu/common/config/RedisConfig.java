package com.lmsltirollcallsjtu.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmsltirollcallsjtu.common.properties.RedisProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * @author wzj
     * @createdDate xxxx.xx.xx
     * @description 默认情况下RedisTemplate使用的是二进制存储数据。这里自定义RedisTemplate配置使用Json序列化存储数据。（相比，Json更直观，而二进制存取速度快）
     * @param redisConnectionFactory
     * @result RedisTemplate<Object,Object>
     * */
    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<Object, Object> template = new RedisTemplate<Object,Object>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }

    /**
     * @author wzj
     * @createdDate 2019.08.20
     * @description redis分布式锁工具类
     * @param
     * @result Redisson
     * */
    @Bean
    public Redisson redisson() {

        //redis单机模式配置
        Config config = new Config();

        config.useSingleServer()
              .setAddress("redis://"+redisProperties.getHost()+":"+redisProperties.getPort())
              .setDatabase(redisProperties.getDatabase());

        RedissonClient redissonClient = Redisson.create(config);
        //可通过打印redisson.getConfig().toJSON().toString()来检测是否配置成功
        return (Redisson) redissonClient;
    }

}

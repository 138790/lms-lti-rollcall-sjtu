package com.lmsltirollcallsjtu.common.config;//package com.lmsauthsjtu.common.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class RedisConfig {
//
//    @Value("${spring.redis.cluster.nodes}")
//    private  String cluster;
//
//    /**
//     * @author wzj
//     * @createdDate xxxx.xx.xx
//     * @description 默认情况下RedisTemplate使用的是二进制存储数据。这里自定义RedisTemplate配置使用Json序列化存储数据。（相比，Json更直观，而二进制存取速度快）
//     * @param redisConnectionFactory
//     * @result RedisTemplate<Object,Object>
//     * */
//    @Bean
//    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//
//        RedisTemplate<Object, Object> template = new RedisTemplate<Object,Object>();
//        template.setConnectionFactory(redisConnectionFactory);
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(jackson2JsonRedisSerializer);
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);
//
//        template.afterPropertiesSet();
//        return template;
//    }
//
//    /**
//     * @author wzj
//     * @createdDate 2019.08.20
//     * @description redis分布式锁工具类
//     * @param
//     * @result Redisson
//     * */
//    @Bean
//    public Redisson redisson() {
//
////        //redis单机模式配置
////        Config config = new Config();
////        config.useSingleServer()
////              .setAddress("redis://127.0.0.1:6379")
////              .setDatabase(0);
//
//
//        //获取集群所有节点地址集
//        String[] nodes = cluster.split(",");
//        //redisson版本是3.5，集群的ip前面要加上“redis://”，不然会报错，3.2版本可不加
//        for(int i=0;i<nodes.length;i++){
//            nodes[i] = "redis://"+nodes[i];
//        }
//
//        //redis集群配置
//        Config config = new Config();
//        config.useClusterServers() //这是用的集群server
//              .setScanInterval(2000) //设置集群状态扫描时间
//              .addNodeAddress(nodes);
//
//        RedissonClient redissonClient = Redisson.create(config);
//        //可通过打印redisson.getConfig().toJSON().toString()来检测是否配置成功
//        return (Redisson) redissonClient;
//    }
//
//}

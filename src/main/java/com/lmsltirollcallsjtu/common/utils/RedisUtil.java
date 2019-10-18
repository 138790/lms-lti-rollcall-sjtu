package com.lmsltirollcallsjtu.common.utils;//package com.lmsauthsjtu.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * RedisUtil工具类
 * 小知识点扩展：
 * new RedisUtil()不会触发其属性的@Autowired自动注入，所以需要将RedisUtil的实例化过程交给spring管理，@Autowired注入RedisUtil实例。
 * 注意这里是静态变量和方法，所以启动时加载静态资源，便会初始化自动注入（@Autowired）静态属性值。所以使用RedisUtil类可以直接调用静态方法。
 */
@Component
public class RedisUtil {


    private static StringRedisTemplate stringRedisTemplate;

    private static RedisTemplate<Object,Object> redisTemplate;

    //@Autowired、@Resource均无法直接注解在静态属性和方法上，但可以用@Autowired注解标注Setter方法实现变向引用注入
    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        RedisUtil.stringRedisTemplate = stringRedisTemplate;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }


//===================================String操作=======================================================
    public static void setString(String key, String str, Long expirationTime, TimeUnit timeUnit){

        stringRedisTemplate.opsForValue().set(key,str,expirationTime,timeUnit);
    }

    public static String getString(String key){

        return stringRedisTemplate.opsForValue().get(key);
    }

    public static void deleteString(String key){

        stringRedisTemplate.delete(key);
    }

//===================================Object操作=======================================================
    public static void setObject(String key, Object obj, Long expirationTime, TimeUnit timeUnit){

        redisTemplate.opsForValue().set(key,obj,expirationTime,timeUnit);
    }

    public static Object getObject(String key){

        return redisTemplate.opsForValue().get(key);
    }

    public static void delete(String key){

        redisTemplate.delete(key);
    }

//===================================List操作(左进右出队列，有序且可重复)=======================================================
    public static void leftAddToList(String key, Object obj){

        redisTemplate.opsForList().leftPush(key,obj);
    }

    public static Object rightGetFromList(String key){

        return redisTemplate.opsForList().rightPop(key);
    }

    public static void leftAddAllToList(String key, List<Object> list){

        redisTemplate.opsForList().leftPushAll(key,list);
    }

    public static void deleteValueFromList(String key, String hashKey, Object obj){

        redisTemplate.opsForList().remove(key,0,obj);
    }

//===================================HashMap操作（无序、键不重复、值可重复）=======================================================
    public static void putToMap(String key, String hashKey, Object obj){

        redisTemplate.opsForHash().put(key,hashKey,obj);
    }

    public static Object getValueFromMap(String key, String hashKey){

        return redisTemplate.opsForHash().get(key,hashKey);
    }

    public static Map<Object,Object> getMap(String key){

        return redisTemplate.opsForHash().entries(key);
    }

    public static Set<Object> getMapKeys(String key){

        return redisTemplate.opsForHash().keys(key);
    }

    public static void getMapValues(String key){

        redisTemplate.opsForHash().values(key);
    }

    public static void deleteHashKey(String key, String hashKey){

        redisTemplate.opsForHash().delete(key,hashKey);
    }

//===================================Set操作（无序但不可重复）=======================================================
    public static void addToSet(String key, Object obj){

        redisTemplate.opsForSet().add(key,obj);
    }

    public static Object randomGetValueFromSet(String key){

        return redisTemplate.opsForSet().randomMember(key);
    }

    public static Set getSet(String key){

        return redisTemplate.opsForSet().members(key);
    }

    public static void deleteValueFromSet(String key, Object obj){

        redisTemplate.opsForSet().remove(key,obj);
    }

//===================================ZSet操作（有序但不可重复，依据分数规则排序）=========================================




}

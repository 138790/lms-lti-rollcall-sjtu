package com.lmsltirollcallsjtu.common.utils;

import java.util.HashMap;
import java.util.Map;

public class ExecutionContext {

    /**
     * 用于保存线程相关信息
     */
    transient static ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<Map<String, String>>();


    public static final String USER_CODE = "user_code";

    /**
     * 构造函数
     */
    public ExecutionContext() {
    }

    /**
     * 从 ThreadLocal中获取名值Map(不包含appCode)
     *
     * @return 名值Map
     */
    public static Map<String, String> getContextMap() {
        Map<String, String> map = threadLocal.get();
        return map;
    }

    /**
     * 从 ThreadLocal 获取名值Map
     *
     * @param contextMap
     *            名值Map
     */
    public static void setContextMap(Map<String, String> contextMap) {
        threadLocal.set(contextMap);
    }

    /**
     * （获取键下的值.如果不存在，返回null；如果名值Map未初始化，也返回null） Get the value of key. Would
     * return null if context map hasn't been initialized.
     *
     * @param key
     *            键
     * @return 键下的值
     */
    public static String get(String key) {
        Map<String, String> contextMap = getContextMap();
        if (contextMap == null) {
            return null;
        }
        return contextMap.get(key);
    }

    /**
     * （设置名值对。如果Map之前为null，则会被初始化） Put the key-value into the context map;
     * <p>
     * Initialize the map if the it doesn't exist.
     *
     * @param key
     *            键
     * @param value
     *            值
     * @return 之前的值
     */
    public static String put(String key, String value) {
        Map<String, String> contextMap = getContextMap();
        if (contextMap == null) {
            contextMap = new HashMap<String, String>();
            setContextMap(contextMap);
        }

        return contextMap.put(key, value);
    }

    public static String getUserCode() {
        return get(USER_CODE);
    }

    public static void setUserCode(String userCode) {
        put(USER_CODE, userCode);
    }

}

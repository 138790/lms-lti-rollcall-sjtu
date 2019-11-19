package com.lmsltirollcallsjtu.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangzhijun
 * @createdDate 2019-10-22
 * @description 自定义UserLoginToken注解，凡是Controller层标注了UserLoginToken注解的方法均需要进行用户token验证
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {

    boolean required() default true;
}

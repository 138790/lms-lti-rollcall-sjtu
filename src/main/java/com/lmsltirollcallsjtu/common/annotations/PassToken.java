package com.lmsltirollcallsjtu.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangzhijun
 * @createdDate 2019-10-22
 * @description 自定义PassToken注解，凡是Controller层标注了PassToken注解的方法拦截器均跳过用户token验证
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {

    boolean required() default true;
}

package com.lmsltirollcallsjtu.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author wangzhijun
 * @createdDate 2019-10-22
 * @description 过滤器的跨域配置
 **/
@Configuration
public class CorsFilterConfig {

    /**
     * @author wangzhijun
     * @createdDate 2019-10-22
     * @description 过滤器添加跨域配置
     * @param
     * @return void
     **/
    @Bean
    public CorsFilter corsFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //添加跨域配置
        source.registerCorsConfiguration("/**", buildCorsConfiguration());
        return new CorsFilter(source);
    }

    /**
     * @author wangzhijun
     * @createdDate 2019-10-22
     * @description 构建跨域配置（内部私有方法）
     * @param
     * @return CorsConfiguration
     **/
    private CorsConfiguration buildCorsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //1.设置访问源地址
        corsConfiguration.addAllowedOrigin("*");
        //2.设置访问源请求头
        corsConfiguration.addAllowedHeader("*");
        //3.设置访问源请求方法
        corsConfiguration.addAllowedMethod("*");
        //4.是否允许证书
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }
}

package com.lmsltirollcallsjtu.common.config;

import com.lmsltirollcallsjtu.common.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wangzhijun
 * @createdDate 2019-10-22
 * @description 注册自定义拦截器
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * @author wangzhijun
     * @createdDate 2019-10-22
     * @description 实例化AuthenticationInterceptor自定义拦截器
     * @param
     * @return void
     **/
    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                //跨域允许时间
                .maxAge(3600);
    }

    /**
     * @author wangzhijun
     * @createdDate 2019-10-22
     * @description 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
     *             （链式Intercepter情况下，Intercepter按照声明的顺序一个接一个执行）
     * @param registry
     * @return void
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //所有已demo开头的访问都要进入RedisSessionInterceptor拦截器进行登录验证，并排除login接口(全路径)。
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        // 必须写成链式，分别设置的话会创建多个拦截器。
        //必须写成getSessionInterceptor()，否则SessionInterceptor中的@Autowired会无效
//        registry.addInterceptor(getSessionInterceptor()).addPathPatterns("/demo/**").excludePathPatterns("/demo/user/login");
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("*.js", "/**/*.js", "*.css", "/**/*.css", "*.html", "/**/*.html")     //排除静态文件
                .excludePathPatterns("/error","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");   //排除swagger
        //在Spring Boot较新版本中，下行这里直接去掉，否则会报错
        //super.addInterceptors(registry);
    }

    /**
     * @author wangzhijun
     * @createdDate 2019-10-22
     * @description 这个方法是用来配置静态资源的，比如html，js，css，等等
     * @param registry
     * @return void
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}

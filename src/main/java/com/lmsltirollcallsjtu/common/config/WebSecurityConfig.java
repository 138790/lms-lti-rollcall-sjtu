package com.lmsltirollcallsjtu.common.config;//package com.lmsauthsjtu.common.config;
//
//import com.example.demo.common.Interceptor.RedisSessionInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// *注册自定义拦截器
// **/
//@Configuration
//public class WebSecurityConfig implements WebMvcConfigurer {
//
//    /**
//     * @description 实例化RedissSession自定义拦截器
//     * */
//    @Bean
//    public RedisSessionInterceptor getSessionInterceptor() {
//
//        return new RedisSessionInterceptor();
//    }
//
//    /**
//     * @description 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
//     * （链式Intercepter情况下，Intercepter按照声明的顺序一个接一个执行）
//     * */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        //所有已demo开头的访问都要进入RedisSessionInterceptor拦截器进行登录验证，并排除login接口(全路径)。
//        // addPathPatterns("/**") 表示拦截所有的请求，
//        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
//        // 必须写成链式，分别设置的话会创建多个拦截器。
//        //必须写成getSessionInterceptor()，否则SessionInterceptor中的@Autowired会无效
//        registry.addInterceptor(getSessionInterceptor()).addPathPatterns("/demo/**").excludePathPatterns("/demo/user/login");
//
//        //在Spring Boot较新版本中，下行这里直接去掉，否则会报错
//        //super.addInterceptors(registry);
//    }
//
//    /**
//     * @description 这个方法是用来配置静态资源的，比如html，js，css，等等
//     * */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//    }
//
//}

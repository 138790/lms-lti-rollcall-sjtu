package com.lmsltirollcallsjtu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@EnableFeignClients
@ServletComponentScan
@EnableScheduling
@EnableAsync
@MapperScan(basePackages = "com.lmsltirollcallsjtu.*.dao")
public class LmsLtiRollcallSjtuApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmsLtiRollcallSjtuApplication.class, args);
    }

//    @Bean
//    public ServletRegistrationBean dispatcherServlet() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(
//                new DispatcherServlet(), "/");
//        registration.setAsyncSupported(true);
//        return registration;
//    }

}

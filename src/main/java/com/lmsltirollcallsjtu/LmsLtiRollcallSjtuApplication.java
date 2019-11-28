package com.lmsltirollcallsjtu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableFeignClients
@ServletComponentScan
@EnableScheduling
@MapperScan(basePackages = "com.lmsltirollcallsjtu.*.dao")
@EnableTransactionManagement
public class LmsLtiRollcallSjtuApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmsLtiRollcallSjtuApplication.class, args);
    }

}

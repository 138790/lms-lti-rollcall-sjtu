package com.lmsltirollcallsjtu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@MapperScan(basePackages = "com.lmsltirollcallsjtu.*.dao")
public class LmsLtiRollcallSjtuApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmsLtiRollcallSjtuApplication.class, args);
    }

}

package com.lmsltirollcallsjtu.common.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "server.servlet")
@Data
public class OurServerProperties {

    private String contextPath;   //应用服务路径
    private String sign;   //服务器签名

}

package com.lmsltirollcallsjtu.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Data
public class RabbitMQProperties {

    private String host;
    private Integer port;
    private String username;
    private String password;
    private String virtualHost;
    private Boolean publisherReturns;
}

package com.lmsltirollcallsjtu.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.test")
@Data
public class TestProperties {

    private Boolean enabled;
    private Integer maxAttempts;
    private Integer initialInterval;
    private String acknowledgeMode;
}

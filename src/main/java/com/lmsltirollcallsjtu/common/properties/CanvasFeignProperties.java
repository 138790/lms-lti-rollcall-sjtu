package com.lmsltirollcallsjtu.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "feign.client")
@Data
public class CanvasFeignProperties {

    private String supperAdminToken;
}

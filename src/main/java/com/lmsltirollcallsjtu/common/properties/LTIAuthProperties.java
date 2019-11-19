package com.lmsltirollcallsjtu.common.properties;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "lti-auth")
@Setter
public class LTIAuthProperties {

    private String oauthConsumerKey;
    private String secret;

    public String getSecret(String oauthConsumerKey){
        boolean flag = (this.oauthConsumerKey).equals(oauthConsumerKey);
        if(flag){
            return secret;
        }else{
            return "";
        }
    }
}

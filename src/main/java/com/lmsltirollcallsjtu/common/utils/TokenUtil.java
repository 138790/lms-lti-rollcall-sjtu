package com.lmsltirollcallsjtu.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lmsltirollcallsjtu.common.properties.OurServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * @author wangzhijun
 * @createdDate 2019-10-22
 * @description token工具
 **/
@Component
public class TokenUtil {

    @Autowired
    private static OurServerProperties ourServerProperties;

    /**
     * @author huyong
     * @createdDate 2019-11-22
     * @description 生成token
     * @parameter userInfoVo
     * @return token字符串
     **/
    public static String generateRecordToken(String signRecordId) {

        String singScanToken="";
        singScanToken= JWT
                .create()
                .withAudience(signRecordId,new Date().toString())
                .sign(Algorithm.HMAC256(ourServerProperties.getSign()));


        return singScanToken;
    }
}

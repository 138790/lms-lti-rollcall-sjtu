package com.lmsltirollcallsjtu.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lmsltirollcallsjtu.common.bean.bo.UserInfo;
import org.springframework.stereotype.Component;

/**
 * @author wangzhijun
 * @createdDate 2019-10-22
 * @description token工具
 **/
@Component
public class SecretUtil {

    /**
     * @author wangzhijun
     * @createdDate 2019-10-22
     * @description 生成token
     * @param userInfoVo
     * @return token字符串
     **/
    public static String generateSecret(UserInfo userInfo) {

        String secret="";

        secret= JWT
                .create()
                .withAudience(userInfo.getUserCode().toString())
                .sign(Algorithm.HMAC256(userInfo.getSign()));

        return secret;
    }
}

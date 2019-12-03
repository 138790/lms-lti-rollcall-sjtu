package com.lmsltirollcallsjtu.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lmsltirollcallsjtu.common.bean.vo.UserRecordVo;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author wangzhijun
 * @createdDate 2019-10-22
 * @description token工具
 **/
@Component
public class TokenUtil {


    /**
     * @author huyong
     * @createdDate 2019-11-22
     * @description 生成token
     * @parameter userInfoVo
     * @return token字符串
     **/
    public static String generateToken(UserRecordVo userRecordVo) {

        String token="";
        System.out.println(userRecordVo);
        token= JWT
                .create()
                .withAudience(userRecordVo.getId(),new Date().toString())
                .sign(Algorithm.HMAC256(userRecordVo.getSign()));


        return token;
    }
}

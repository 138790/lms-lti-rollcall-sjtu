package com.lmsltirollcallsjtu.common.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lmsltirollcallsjtu.common.bean.bo.UserInfo;
import org.springframework.scheduling.annotation.Scheduled;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author huyong
 * @createdDate 2019-10-29
 * @description secret工具
 **/

public class SecretUtil {


    /**
     * @author huyong
     * @createdDate 2019-10-29
     * @description 生成secret
     * @param userInfo
     * @return secret字符串
     **/
   // @Scheduled(cron="0/2 * *  * * ? ")
    public static String generateSecret(UserInfo userInfo) {

        Calendar c = new GregorianCalendar();
        Date date = new Date();
        c.setTime(date);//设置参数时间
        c.add(Calendar.SECOND,+2);//把日期往后增加2秒.整数往后推,负数往前移动
        date=c.getTime(); //这个时间就是日期往后推两秒的结果
        String secret="";
           secret= JWT
                   .create()
                   .withAudience(userInfo.getLoginName())
                   .withExpiresAt(date)
                   .sign(Algorithm.HMAC256(userInfo.getSign()));

        return secret;
    }
}

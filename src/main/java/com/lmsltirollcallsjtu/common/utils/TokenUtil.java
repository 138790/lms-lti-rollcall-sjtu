package com.lmsltirollcallsjtu.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
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


    private static OurServerProperties ourServerProperties;
    @Autowired
    public void setOurServerProperties(OurServerProperties ourServerProperties){
        TokenUtil.ourServerProperties=ourServerProperties;
    }
    /**
     * @author huyong
     * @createdDate 2019-11-22
     * @description 生成token
     * @parameter userInfoVo
     * @return token字符串
     **/
    public static String generateSignScanToken(String signHistoryId) {

        String signScanToken="";
        signScanToken= JWT
                .create()
                .withAudience( signHistoryId,new Date().toString())
                .sign(Algorithm.HMAC256(ourServerProperties.getSign()));


        return signScanToken;
    }

    /**
     * @author huyong
     * @createdDate 2019-11-22
     * @description 验证token
     * @parameter userInfoVo
     * @return signHistoryId
     **/
    public static String verifySignScanToken(String signScanToken) throws BusinessException {


        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(ourServerProperties.getSign())).build();
        try{
            jwtVerifier.verify(signScanToken);
        }catch(JWTVerificationException e){
            throw BusinessException.getInstance(BusinessExceptionEnum.VERIFY_TOKEN_FAILURE);
        }
        //从token中解析出signRecordId
        String signHistoryId = JWT.decode(signScanToken).getAudience().get(0);
        String signScanTokenFromRedis =(String) RedisUtil.getValueFromMap("signScanTokens", signHistoryId);

        if (!signScanTokenFromRedis.equals(signScanToken)){
            throw BusinessException.getInstance(BusinessExceptionEnum.VERIFY_SIGN_SCAN_TOKEN_FAILED);
        }

        return signHistoryId;
    }
}

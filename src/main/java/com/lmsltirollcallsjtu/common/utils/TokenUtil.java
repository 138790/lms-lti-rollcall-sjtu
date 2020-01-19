package com.lmsltirollcallsjtu.common.utils;

import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.properties.OurServerProperties;
import org.apache.commons.codec.digest.DigestUtils;
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

        String suffix= DigestUtils.md5Hex(ourServerProperties.getSign()+new Date());
        String signScanToken=signHistoryId+":"+suffix;
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

        if ("1".equals(RedisUtil.getString("rollcallToken:" + signScanToken))) {
            String[] tokenArray = signScanToken.split(":");
            String signHistoryId = tokenArray[1];
            return signHistoryId;
        } else {
            throw BusinessException.notFoundData("signScanToken:"+signScanToken);
        }

    }
}

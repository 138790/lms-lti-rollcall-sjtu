package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.ScanSignBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.UserInfo;
import com.lmsltirollcallsjtu.common.bean.po.SignRecords;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.ScanSignServcie;
import com.lmsltirollcallsjtu.common.utils.RedisUtil;
import com.lmsltirollcallsjtu.common.utils.SecretUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScanSignSercieImpl implements ScanSignServcie {
    @Autowired
    private ScanSignBasicService scanSignBasicService;
    @Autowired
    private UserInfo userInfo;
    @Override
    public void insertObject(SignRecords signRecords) {

        if(SecretUtil.generateSecret(userInfo)== RedisUtil.getValueFromMap("userInfos",userInfo.getLoginName())){
            scanSignBasicService.insertObject(signRecords);
        }
    }
}

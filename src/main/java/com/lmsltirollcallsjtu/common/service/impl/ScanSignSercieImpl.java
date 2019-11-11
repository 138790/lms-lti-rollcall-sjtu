package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.ScanSignBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.UserInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.service.ScanSignServcie;
import com.lmsltirollcallsjtu.common.utils.RedisUtil;
import com.lmsltirollcallsjtu.common.utils.SecretUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScanSignSercieImpl implements ScanSignServcie {
    @Autowired
    private ScanSignBasicService scanSignBasicService;
    @Override
    public void insertObject(SignRecordsDto signRecordsDto) {
        //判断学生传进来的token与系统生成的token是否一致
        UserInfo userInfo = new UserInfo();
        if(SecretUtil.generateSecret(userInfo)== RedisUtil.getValueFromMap("userInfos",userInfo.getLoginName())){
            scanSignBasicService.insertObject(signRecordsDto);
        }
    }
}
package com.lmsltirollcallsjtu.common.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lmsltirollcallsjtu.common.base.service.ScanSignBasicService;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordDto;
import com.lmsltirollcallsjtu.common.bean.vo.UserRecordVo;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.properties.OurServerProperties;
import com.lmsltirollcallsjtu.common.service.ScanSignServcie;
import com.lmsltirollcallsjtu.common.utils.RedisUtil;
import com.lmsltirollcallsjtu.common.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ScanSignSercieImpl implements ScanSignServcie {
    @Autowired
    private ScanSignBasicService scanSignBasicService;
    @Autowired
    private OurServerProperties ourServerProperties;
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void scanUpdateState(SignRecordDto signRecordDto) throws BusinessException {
        Long userCode=signRecordDto.getUserCode();
        String rollcallCode=signRecordDto.getId();
        if (scanSignBasicService.queryStateByRecordId(rollcallCode,userCode).equals("NORMAL")){
            throw BusinessException.getInstance(BusinessExceptionEnum.ALREADY_EXISTS);
        }
        //生成token,给signToken赋值
        UserRecordVo userRecordVo = UserRecordVo.builder()
                                                .id(signRecordDto.getId())
                                                .sign(ourServerProperties.getSign())
                                                .build();
        String token = TokenUtil.generateToken(userRecordVo);
        signRecordDto.setSignToken(token);
        //验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(ourServerProperties.getSign())).build();
        try{
            jwtVerifier.verify(signRecordDto.getSignToken());
        }catch(JWTVerificationException e){
            throw BusinessException.getInstance(BusinessExceptionEnum.VERIFY_TOKEN_FAILURE);
        }
//        从token中解析出id
        String id = JWT.decode(signRecordDto.getSignToken()).getAudience().get(0);

        String signScans =(String) RedisUtil.getValueFromMap("signScans", id);
        signRecordDto.setRollcallCode(signRecordDto.getId());
        if (signRecordDto.getSignToken().equals(signScans)){
            signRecordDto.setState("NORMAL");
            signRecordDto.setUpdatedDate(new Date());
            signRecordDto.setUpdatedBy(signRecordDto.getUserCode().toString());
            scanSignBasicService.scanUpdateState(signRecordDto);
        }else {
            throw BusinessException.getInstance(BusinessExceptionEnum.CODE_SWEEP_FAILED);
        }

    }
}

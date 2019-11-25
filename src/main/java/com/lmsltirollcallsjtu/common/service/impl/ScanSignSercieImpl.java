package com.lmsltirollcallsjtu.common.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lmsltirollcallsjtu.common.base.service.ScanSignBasicService;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordDto;
import com.lmsltirollcallsjtu.common.bean.vo.UserRecordVo;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.ScanSignServcie;
import com.lmsltirollcallsjtu.common.utils.RedisUtil;
import com.lmsltirollcallsjtu.common.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class ScanSignSercieImpl implements ScanSignServcie {
    @Autowired
    private ScanSignBasicService scanSignBasicService;
    @Override
    public void scanUpdateState(SignRecordDto signRecordDto) throws BusinessException {
        //将token存入到redis中
        UserRecordVo userInfoVo = UserRecordVo.builder()
                                          .id(signRecordDto.getId())
                                          .date(new Date())
                                          .build();
        String token = TokenUtil.generateToken(userInfoVo);
        String recordId=signRecordDto.getId();
        RedisUtil.putToMap("userScans", recordId,token);
        //获取userCode
            try {
                recordId = JWT.decode(token).getAudience().get(0);
            }catch(JWTDecodeException j){
                throw BusinessException.getInstance(BusinessExceptionEnum.DECODE_TOKEN_ERROR);
            }
            //如果用户不存在则抛出异常
            if ( recordId==null){
                throw BusinessException.getInstance(BusinessExceptionEnum.NOT_FOUND_USER);
            }
            //验证token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userInfoVo.getDate().toString())).build();
            try{
                jwtVerifier.verify(token);
            }catch(JWTVerificationException e){
                throw BusinessException.getInstance(BusinessExceptionEnum.VERIFY_TOKEN_FAILURE);
            }
            if (scanSignBasicService.queryStateByRecordId(recordId).equals("NORMAL")){
                throw BusinessException.getInstance(BusinessExceptionEnum.ALREADY_EXISTS);
        }
            signRecordDto.setState("NORMAL");
            scanSignBasicService.scanUpdateState(signRecordDto);

    }
}

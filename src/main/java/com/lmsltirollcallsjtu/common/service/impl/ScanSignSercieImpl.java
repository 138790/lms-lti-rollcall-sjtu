package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.ScanSignBasicService;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordDto;
import com.lmsltirollcallsjtu.common.bean.param.UpdateSignHistoryStateParam;
import com.lmsltirollcallsjtu.common.enums.SignInStateEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.ScanSignServcie;
import com.lmsltirollcallsjtu.common.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void scanUpdateState(UpdateSignHistoryStateParam updateSignHistoryStateParam) throws BusinessException {

        //1.获取系统当前时间
        Date nowDate = new Date();

        //2.验证signScanToken
        String signHistoryId = TokenUtil.verifySignScanToken(updateSignHistoryStateParam.getSignScanToken());

        //3.查询本次点名该用户的签到状态
        String SignInState = scanSignBasicService.queryStateByRecordId(signHistoryId, updateSignHistoryStateParam.getUserCode());
        if(StringUtils.isBlank(SignInState)){
            throw BusinessException.notFoundData("点名历史"+signHistoryId);
        }

        //4.如果用户没有正常签到，则做更新状态操作，否则直接跳过
        if (!SignInState.equals(SignInStateEnum.NORMAL.getCode())){
            SignRecordDto signRecordDto = SignRecordDto.builder().rollcallCode(signHistoryId)
                    .state(SignInStateEnum.NORMAL.getCode())
                    .updatedBy(String.valueOf(updateSignHistoryStateParam.getUserCode()))
                    .updatedDate(nowDate).build();
            scanSignBasicService.scanUpdateState(signRecordDto);
        }
    }
}

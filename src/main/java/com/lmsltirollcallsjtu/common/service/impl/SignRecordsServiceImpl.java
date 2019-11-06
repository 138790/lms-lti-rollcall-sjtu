package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.SignRecordsBasicService;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.SignRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
public class SignRecordsServiceImpl implements SignRecordsService {
    @Autowired
    private SignRecordsBasicService signRecordsBasicService;
    @Override
    public List<SignRecordsDto> findSignHistoryByUserCode(Long userCode) throws BusinessException {
        if(userCode==null||userCode<1){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }
        return signRecordsBasicService.findSignHistoryByUserCode(userCode);
    }

    @Override
    public SignRecordsDto findSignConditionByRollcallCode(String rollcallCode) throws BusinessException {
        if(StringUtils.isEmpty(rollcallCode)){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }
        return signRecordsBasicService.findSignConditionByRollcallCode(rollcallCode);
    }
}

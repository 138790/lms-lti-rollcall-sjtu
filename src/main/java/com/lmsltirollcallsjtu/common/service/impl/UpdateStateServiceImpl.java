package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.UpdateStateBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecords;
import com.lmsltirollcallsjtu.common.bean.bo.UserStates;
import com.lmsltirollcallsjtu.common.bean.dto.DictionaryDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.UpdateStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class UpdateStateServiceImpl implements UpdateStateService {

    @Autowired
    private UpdateStateBasicService updateStateBasicService;
    @Override
    public void updateUserStateByUserStates(String dictCode,UserStates userStates) throws BusinessException {
        if (StringUtils.isEmpty(dictCode)){
            throw BusinessException.getInstance(BusinessExceptionEnum.NOT_DATA_FOUND);
        }
        if (StringUtils.isEmpty(userStates.getRollcallCode())){
            throw BusinessException.getInstance(BusinessExceptionEnum.NOT_DATA_FOUND);
        }
        String dictType="ROLLCALL_STATE";
        List<DictionaryDto> dictionaryDtos = updateStateBasicService.queryRollcallStatesByDictType(dictType);
        for (DictionaryDto item:dictionaryDtos){
            if (item.getDictCode().equals(dictCode)){
                userStates.setState(item.getDictCode());
                userStates.setUpdatedBy(userStates.getUserCode().toString());
                userStates.setUpdatedDate(new Date());
            }
        }

        updateStateBasicService.updateUserStateByUserStates(userStates);
    }
}

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

import java.util.List;

@Service
public class UpdateStateServiceImpl implements UpdateStateService {

    @Autowired
    private UpdateStateBasicService updateStateBasicService;
    @Override
    public void updateUserStateByUserStates(String rollcallCode,List<UserStates> userStateList) throws BusinessException {
        String dictType="ROLLCALL_STATE";
        List<DictionaryDto> dictionaryDtos = updateStateBasicService.queryRollcallStatesByDictType(dictType);

        for (DictionaryDto item:dictionaryDtos){
            for (UserStates userStates:userStateList){
                userStates.setUpdatedBy(userStates.getUserCode().toString());
                if (item.getDictCode()==userStates.getState()){
                    userStates.setState(item.getDictCode());
                }
            }
        }
        updateStateBasicService.updateUserStateByUserStates(rollcallCode,userStateList);
    }
}

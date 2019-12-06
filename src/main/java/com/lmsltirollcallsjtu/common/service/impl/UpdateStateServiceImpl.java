package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.UpdateStateBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.UserStates;
import com.lmsltirollcallsjtu.common.bean.dto.DictionaryDto;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.UpdateStateService;
import com.lmsltirollcallsjtu.common.utils.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class UpdateStateServiceImpl implements UpdateStateService {

    @Autowired
    private UpdateStateBasicService updateStateBasicService;
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void updateUserStateByUserStates(UserStates userStates) throws BusinessException {

        String dictType="ROLLCALL_STATE";
        List<DictionaryDto> dictionaryDtos = updateStateBasicService.queryRollcallStatesByDictType(dictType);
        String updateUserCode = ExecutionContext.getUserCode();
        for (DictionaryDto item:dictionaryDtos){
            if (item.getDictCode().equals(userStates.getState())){
                userStates.setState(item.getDictCode());
                userStates.setUpdatedBy(updateUserCode);
                userStates.setUpdatedDate(new Date());
            }
        }

        updateStateBasicService.updateUserStateByUserStates(userStates);
    }
}

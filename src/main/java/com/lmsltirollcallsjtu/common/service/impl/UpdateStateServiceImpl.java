package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.UpdateStateBasicService;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.service.UpdateStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateStateServiceImpl implements UpdateStateService {
    @Autowired
    private UpdateStateBasicService updateStateBasicService;
    private SignRecordsDto signRecordsDto=new SignRecordsDto();
    @Override
    public void updateUserStateBySignRecordsDto(SignRecordsDto signRecordsDto) {
        if(signRecordsDto.getState()!="normal"){
            signRecordsDto.setState("amend");
        }
        updateStateBasicService.updateUserStateBySignRecordsDto(signRecordsDto);
    }
}

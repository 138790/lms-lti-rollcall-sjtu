package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.SignHistoriesBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.SignHistoryInfo;
import com.lmsltirollcallsjtu.common.bean.bo.UserCourseInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.SignHistoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SignHistoriesServiceImpl implements SignHistoriesService {
    @Autowired
    private SignHistoriesBasicService signHistoriesBasicService;


    @Override
    public SignHistoryInfo findSignHistoryByRollcallId(String id) throws BusinessException {
        if(StringUtils.isEmpty(id)){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }
        SignHistoryInfo signHistoryInfo = signHistoriesBasicService.findSignHistoryByRollcallId(id);
        return signHistoryInfo;
    }

    @Override
    public List<SignHistoryDto> findSignHistoryListByCourseIdAndUserId(UserCourseInfo userCourseInfo) throws BusinessException {
        if(userCourseInfo.getUserCode()==null||userCourseInfo.getUserCode()<1){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }
        if (userCourseInfo.getCourseCode()==null||userCourseInfo.getCourseCode()<1){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }
        return signHistoriesBasicService.findSignHistoryListByCourseIdAndUserId(userCourseInfo);
    }

}

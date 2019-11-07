package com.lmsltirollcallsjtu.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lmsltirollcallsjtu.common.base.service.SignHistoriesBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.Sections;
import com.lmsltirollcallsjtu.common.bean.bo.SignHistoryInfo;
import com.lmsltirollcallsjtu.common.bean.bo.UserCourseInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.config.CanvasFeignProperties;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.feign.CanvasFeignClient;
import com.lmsltirollcallsjtu.common.service.SignHistoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SignHistoriesServiceImpl implements SignHistoriesService {
    @Autowired
    private SignHistoriesBasicService signHistoriesBasicService;
    @Autowired
    private CanvasFeignClient canvasFeignClient;
    @Autowired
    private CanvasFeignProperties canvasFeignProperties;
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


        if(userCourseInfo.getUserId()==null||userCourseInfo.getUserId()<1){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }
        if (userCourseInfo.getCourseId()==null||userCourseInfo.getCourseId()<1){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }

        List<SignHistoryDto> signHistoryDtoList = signHistoriesBasicService.findSignHistoryListByCourseIdAndUserId(userCourseInfo);
        signHistoryDtoList.stream().forEach(item -> item.setSectionCodes(JSON.parseArray(item.getSectionCodesJsonStr(),Long.class)));
        //ResponseEntity<List<Sections>> sections = canvasFeignClient.getSections(canvasFeignProperties.getSupperAdminToken(), userCourseInfo.getCourseId());

        return signHistoryDtoList;
    }

}

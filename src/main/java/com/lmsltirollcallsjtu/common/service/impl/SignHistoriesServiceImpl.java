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
import org.springframework.http.HttpHeaders;
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


        if(userCourseInfo.getUserCode()==null||userCourseInfo.getUserCode()<1){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }
        if (userCourseInfo.getCourseCode()==null||userCourseInfo.getCourseCode()<1){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }
        List<SignHistoryDto> signHistoryDtoList = signHistoriesBasicService.findSignHistoryListByCourseIdAndUserId(userCourseInfo);
        ResponseEntity<List<Sections>> sections = canvasFeignClient.getSections(canvasFeignProperties.getSupperAdminToken(), 540L);
        List<Sections> sectionsBody = sections.getBody();
        HttpHeaders headers = sections.getHeaders();
        signHistoryDtoList.get(0).setSectionName(sectionsBody.get(0).getName());
        signHistoryDtoList.get(0).setTotalStudents(sectionsBody.get(0).getTotal_students());

        signHistoryDtoList.stream().forEach(item -> item.setSectionCodes(JSON.parseArray(item.getSectionCodesJsonStr(),Long.class)));
//        List<String> sectionNameList = sectionsBody.stream().map(item -> item.getName()).collect(Collectors.toList());
        Object[] objects = sectionsBody.stream().map(item -> item.getName()).toArray();


        return signHistoryDtoList;
    }

}

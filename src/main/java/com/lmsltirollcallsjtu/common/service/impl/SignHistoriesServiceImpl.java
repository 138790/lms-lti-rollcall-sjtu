package com.lmsltirollcallsjtu.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lmsltirollcallsjtu.common.base.service.SignHistoriesBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.SectionsOfCanvas;
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
    public List<SignHistoryInfo> findSignHistoryByRollcallId(String id) throws BusinessException {
        if(StringUtils.isEmpty(id)){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }
        List<SignHistoryInfo> signHistoryInfo = signHistoriesBasicService.findSignHistoryByRollcallId(id);
//        canvasFeignClient.getSectionDetail(canvasFeignProperties.getSupperAdminToken(),)

        return signHistoryInfo;
    }

    @Override
    public List<SignHistoryDto> findSignHistoryListByCourseIdAndUserId(Long userCode,Long courseCode) throws BusinessException {


        if(userCode==null||userCode<1){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }
        if (courseCode==null||courseCode<1){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }
        List<SignHistoryDto> signHistoryDtoList = signHistoriesBasicService.findSignHistoryListByCourseIdAndUserId(userCode,courseCode);
        ResponseEntity<List<SectionsOfCanvas>> sections = canvasFeignClient.getSections(canvasFeignProperties.getSupperAdminToken(),courseCode,"total_students");
        List<SectionsOfCanvas> sectionsOfCanvas = sections.getBody();
        HttpHeaders headers = sections.getHeaders();

        sectionsOfCanvas.stream().forEach(item->{
           SignHistoryDto signHistoryDto = SignHistoryDto.builder()
                   .sectionCodes(item.getId().toString())
                   .sectionName(item.getName())
                   .totalStudents(item.getTotal_students())
                   .createdDate(item.getCreated_at())
                   .build();
           signHistoryDtoList.add(signHistoryDto);
       });

//        Long items=0L;
//        for (Sections section :sectionsBody) {
//          items+=section.getTotal_students();
//        }
//        signHistoryDtoList.get(0).setTotalStudents(items);


//        signHistoryDtoList.stream().forEach(item ->item.setSectionName(item.getSectionName()));

//        signHistoryDtoList.stream().forEach(item -> item.setSectionCodes(JSON.parseArray(item.getSectionCodesJsonStr(),Long.class)));
//        List<String> sectionNameList = sectionsBody.stream().map(item -> item.getName()).collect(Collectors.toList());
//        Object[] objects = sectionsBody.stream().map(item -> item.getName()).toArray();


        return signHistoryDtoList;
    }

}

package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.SignRecordsBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.SectionsOfCanvas;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordInfo;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsOfCourse;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.config.CanvasFeignProperties;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.feign.CanvasFeignClient;
import com.lmsltirollcallsjtu.common.service.SignRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
@Service
public class SignRecordsServiceImpl implements SignRecordsService {
    @Autowired
    private SignRecordsBasicService signRecordsBasicService;
    @Autowired
    private CanvasFeignClient canvasFeignClient;
    @Autowired
    private CanvasFeignProperties canvasFeignProperties;
    @Override
    public List<SignRecordsDto> findSignHistoryByUserCodeAndCourseCode(SignRecordsOfCourse signRecordsOfCourse) throws BusinessException {
        if(signRecordsOfCourse.getUserCode()==null||signRecordsOfCourse.getUserCode()<1){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }
        if(signRecordsOfCourse.getCourseCode()==null||signRecordsOfCourse.getCourseCode()<1){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }
//        List<String> includeList = new ArrayList<String>();
//      includeList.add("total_students");
//        includeList.add("students");

        List<SignRecordsDto> signRecordsDto = signRecordsBasicService.findSignHistoryByUserCodeAndCourseCode(signRecordsOfCourse);
//        ResponseEntity<SectionsOfCanvas> sectionDetail = canvasFeignClient.getSectionDetail(canvasFeignProperties.getSupperAdminToken(),
//                540L,
//                2041L,
//                includeList);
//        SectionsOfCanvas sectionDetailBody = sectionDetail.getBody();
//        HttpHeaders headers = sectionDetail.getHeaders();
//        signRecordsDto.get(0).setName(sectionDetailBody.getName());
//        signRecordsDto.get(0).setStudents(sectionDetailBody.getStudents());
        return signRecordsDto;
    }

    @Override
    public SignRecordsDto findSignConditionByRollcallCode(SignRecordInfo signRecordInfo) throws BusinessException {
        if(StringUtils.isEmpty(signRecordInfo)){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }

        return signRecordsBasicService.findSignConditionByRollcallCode(signRecordInfo);
    }
}

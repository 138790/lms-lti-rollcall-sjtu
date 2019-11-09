package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.SignRecordsBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.SectionsOfCanvas;
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
    public List<SignRecordsDto> findSignHistoryByUserCode(Long userCode) throws BusinessException {
        if(userCode==null||userCode<1){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }

        List<String> includeList = new ArrayList<String>();
//        includeList.add("total_students");
        includeList.add("students");

        List<SignRecordsDto> signRecordsDto = signRecordsBasicService.findSignHistoryByUserCode(userCode);
        ResponseEntity<SectionsOfCanvas> sectionDetail = canvasFeignClient.getSectionDetail(canvasFeignProperties.getSupperAdminToken(),
                540L,
                2041L,
                includeList);
        SectionsOfCanvas sectionDetailBody = sectionDetail.getBody();
        HttpHeaders headers = sectionDetail.getHeaders();
        signRecordsDto.get(0).setName(sectionDetailBody.getName());
        signRecordsDto.get(0).setStudents(sectionDetailBody.getStudents());
        return signRecordsDto;
    }

    @Override
    public SignRecordsDto findSignConditionByRollcallCode(String rollcallCode) throws BusinessException {
        if(StringUtils.isEmpty(rollcallCode)){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }

        return signRecordsBasicService.findSignConditionByRollcallCode(rollcallCode);
    }
}

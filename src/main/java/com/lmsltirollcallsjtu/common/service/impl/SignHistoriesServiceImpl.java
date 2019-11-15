package com.lmsltirollcallsjtu.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lmsltirollcallsjtu.common.base.service.SignHistoriesBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.Section;
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
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author huyong
 * @date 2019-11-14
 * @Description:老师查询历史签到记录,service处理业务逻辑
 */
@Service
public class SignHistoriesServiceImpl implements SignHistoriesService {
    @Autowired
    private SignHistoriesBasicService signHistoriesBasicService;
    @Autowired
    private CanvasFeignClient canvasFeignClient;
    @Autowired
    private CanvasFeignProperties canvasFeignProperties;
    //老师查询某一次点名签到情况
    @Override
    public List<SignHistoryInfo> findSignHistoryByRollcallId(String id) throws BusinessException {
        if (StringUtils.isEmpty(id)) {
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }
        List<SignHistoryInfo> signHistoryInfo = signHistoriesBasicService.findSignHistoryByRollcallId(id);
        for (SignHistoryInfo item:signHistoryInfo) {
            List<Section> sectionList = JSON.parseArray(item.getSectionListJsonStr(), Section.class);
            item.setSectionList(sectionList);
        }
//        SignHistoryInfo signHistory = new SignHistoryInfo();
//        List<String> includeList = new ArrayList<>();
//        includeList.add("students");
//        ResponseEntity<SectionsOfCanvas> sectionOfCanvas = canvasFeignClient.getSectionDetail(canvasFeignProperties.getSupperAdminToken(),
//                signHistory.getCourseCode(),
//                Integer.valueOf(signHistory.getSectionCodes()),
//                includeList);
//        SectionsOfCanvas sectionCanvas = sectionOfCanvas.getBody();
//        HttpHeaders headers = sectionOfCanvas.getHeaders();

        return signHistoryInfo;
    }
    //根据课程id查看签到历史
    @Override
    public List<SignHistoryDto> findSignHistoryListByCourseCode(Long courseCode) throws BusinessException {


//        if(userCode==null||userCode<1){
//            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
//        }
        if (courseCode == null || courseCode < 1) {
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }
        List<SignHistoryDto> signHistoryDtoList = signHistoriesBasicService.findSignHistoryListByCourseCode(courseCode);
//        ResponseEntity<List<SectionsOfCanvas>> sections = canvasFeignClient.getSections(canvasFeignProperties.getSupperAdminToken(),courseCode,"total_students");
//        List<SectionsOfCanvas> sectionsOfCanvas = sections.getBody();
//        HttpHeaders headers = sections.getHeaders();
//        SignHistoryDto signHistoryDto = new SignHistoryDto();
//        SignHistoryDto signHistoryDto = SignHistoryDto.builder().build();
        List<Section> sectionList;
        for (SignHistoryDto item:signHistoryDtoList) {
            sectionList = JSON.parseArray(item.getSectionListJsonStr(), Section.class);
            item.setSectionList(sectionList);
        Long totalOfCourseStudentTemp = 0L;
            for (Section section : sectionList) {
                totalOfCourseStudentTemp += section.getStudentTotal();
            }
            item.setTotalStudents(totalOfCourseStudentTemp);
        }
//            SignHistoryDto signHistoryDto = SignHistoryDto.builder().totalStudents(totalOfCourseStudentTemp).sectionCodes(sectionList).build();
//            signHistoryDtoList.add(signHistoryDto);




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

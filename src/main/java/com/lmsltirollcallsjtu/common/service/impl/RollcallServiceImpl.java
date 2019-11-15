package com.lmsltirollcallsjtu.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.lmsltirollcallsjtu.common.base.service.RollcallBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.*;
import com.lmsltirollcallsjtu.common.bean.param.SignHistoryParam;
import com.lmsltirollcallsjtu.common.config.CanvasFeignProperties;
import com.lmsltirollcallsjtu.common.feign.CanvasFeignClient;
import com.lmsltirollcallsjtu.common.service.RollcallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author huyong
 * @date 2019-11-14
 * @Description:老师发起点名,创建点名记录同时创建学生信息,service处理业务逻辑
 */
@Service
public class RollcallServiceImpl implements RollcallService {
    @Autowired
    private RollcallBasicService rollcallBasicService;
    @Autowired
    private CanvasFeignClient canvasFeignClient;
    @Autowired
    private CanvasFeignProperties canvasFeignProperties;


    @Override
    public void insertSignHistories(SignHistoryParam signHistoryParam) {

        //创建点名记录
        ResponseEntity<List<SectionsOfCanvas>> sectionOfCanvas = canvasFeignClient.getSections(canvasFeignProperties.getSupperAdminToken(),
                                                                 signHistoryParam.getCourseCode(),
                                                                 "total_students");
        List<SectionsOfCanvas> sectionCanvas = sectionOfCanvas.getBody();
        HttpHeaders headers = sectionOfCanvas.getHeaders();
        SectionsOfCanvas sCanvas = new SectionsOfCanvas();

//        for (Section section:signHistory.getSectionList()){
//            section.setStudenttotal(sCanvas.getTotal_students());
//        }
        Long studentTotalOfCourse = 0L;
        List<Section> sections=new ArrayList<>();
        Section sectionTemp = null;
        //从canvas中筛选点名的班级
        for(Long item : signHistoryParam.getSectionCodes()){
            for(SectionsOfCanvas sectionsOfCanvas : sectionCanvas){
                if(sectionsOfCanvas.getId().equals(item)){
                    sectionTemp = Section.builder().sectionCode(sectionsOfCanvas.getId()).sectionName(sectionsOfCanvas.getName()).studentTotal(sectionsOfCanvas.getTotal_students()).build();
                    studentTotalOfCourse += sectionTemp.getStudentTotal();
                }
            }
            if(sectionTemp != null){
                sections.add(sectionTemp);
            }
        }

        SignHistory signHistory = SignHistory.builder().id(UUID.randomUUID().toString().replaceAll("\\-", ""))
                .attendancesCount(0)
                .courseCode(signHistoryParam.getCourseCode())
                .totalStudents(studentTotalOfCourse)
                .sectionList(sections)
                .build();
        signHistory.setCourseCode(signHistoryParam.getCourseCode());
        List<Long> sectionCodeList = signHistory.getSectionList().stream().map(item -> item.getSectionCode()).collect(Collectors.toList());
        sectionCodeList=signHistoryParam.getSectionCodes();
        signHistory.setSectionListJsonStr(JSON.toJSONString(signHistory.getSectionList()));
        rollcallBasicService.insertSignHistories(signHistory);
        //创建点名记录之后创建学生信息记录
        List<String> includeList=new ArrayList<>();
        includeList.add("students");
        List<SignRecordsBo> signRecordsBo=new ArrayList<>();
        for (Section section:signHistory.getSectionList()) {
            ResponseEntity<SectionsOfCanvas> sectionDetail = canvasFeignClient.getSectionDetail(canvasFeignProperties.getSupperAdminToken(),
                    signHistory.getCourseCode(),
                    section.getSectionCode(),
                    includeList);
            SectionsOfCanvas sectionDetailBody = sectionDetail.getBody();
            HttpHeaders httpHeaders = sectionDetail.getHeaders();
            for (Students s : sectionDetailBody.getStudents()) {
                SignRecordsBo recordsBo = SignRecordsBo.builder().id(UUID.randomUUID().toString().replaceAll("\\-", ""))
                        .state("unNormal")
                        .openId("null")
                        .rollcallCode(signHistory.getId())
                        .userCode(s.getId())
                        .userName(s.getName())
                        .build();
                signRecordsBo.add(recordsBo);
            }
        }
        rollcallBasicService.insertStudnetsDetail(signRecordsBo);
    }
}


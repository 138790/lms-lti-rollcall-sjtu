package com.lmsltirollcallsjtu.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.lmsltirollcallsjtu.common.base.service.RollcallBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.*;
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

@Service
public class RollcallServiceImpl implements RollcallService {
    @Autowired
    private RollcallBasicService rollcallBasicService;
    @Autowired
    private CanvasFeignClient canvasFeignClient;
    @Autowired
    private CanvasFeignProperties canvasFeignProperties;


    @Override
    public void insertSignHistories(SignHistory signHistory) {
        //创建点名记录
        ResponseEntity<List<SectionsOfCanvas>> sectionOfCanvas = canvasFeignClient.getSections(canvasFeignProperties.getSupperAdminToken(),
                                                                 signHistory.getCourseCode(),
                                              "total_students");
        List<SectionsOfCanvas> sectionCanvas = sectionOfCanvas.getBody();
        HttpHeaders headers = sectionOfCanvas.getHeaders();
        SectionsOfCanvas sCanvas = new SectionsOfCanvas();
        Integer totalStudents=0;
//        for (Section section:signHistory.getSectionList()){
//            section.setStudenttotal(sCanvas.getTotal_students());
//        }
        Section sectionTemp =new Section();
        List<Section> sections=new ArrayList<>();
        for (SectionsOfCanvas item:sectionCanvas){
                signHistory.setId(UUID.randomUUID().toString().replaceAll("\\-", ""));
                signHistory.setAttendancesCount(0);
                signHistory.setCourseCode(item.getCourse_id());
                totalStudents+=item.getTotal_students();
                signHistory.setTotalStudents(totalStudents);
                sectionTemp=Section.builder().sectionCode(item.getId()).sectionName(item.getName()).studenttotal(item.getTotal_students()).build();
                sections.add(sectionTemp);
                signHistory.setSectionList(sections);
        }
        signHistory.setSectionListJsonStr(JSON.toJSONString(signHistory.getSectionList()));
        rollcallBasicService.insertSignHistories(signHistory);
        //创建点名记录之后创建学生信息
        List<String> includeList=new ArrayList<>();
        includeList.add("students");
        List<SignRecordsBo> signRecordsBo=new ArrayList<>();
        ResponseEntity<SectionsOfCanvas> sectionDetail = canvasFeignClient.getSectionDetail(canvasFeignProperties.getSupperAdminToken(),
                                                         signHistory.getCourseCode(),
                                                         sectionTemp.getSectionCode(),
                                                         includeList);
        SectionsOfCanvas sectionDetailBody = sectionDetail.getBody();
        HttpHeaders httpHeaders = sectionDetail.getHeaders();
        for (Students s:sectionDetailBody.getStudents()) {
            SignRecordsBo recordsBo=SignRecordsBo.builder().id(UUID.randomUUID().toString().replaceAll("\\-", ""))
                                   .state("unNormal")
                                   .openId("null")
                                   .rollcallCode(signHistory.getId())
                                   .userCode(s.getId())
                                   .userName(s.getName())
                                   .build();
            signRecordsBo.add(recordsBo);
        }
        rollcallBasicService.insertStudnetsDetail(signRecordsBo);
    }
}

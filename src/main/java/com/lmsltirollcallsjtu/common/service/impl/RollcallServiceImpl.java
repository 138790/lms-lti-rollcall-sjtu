package com.lmsltirollcallsjtu.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.lmsltirollcallsjtu.common.base.service.RollcallBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.*;
import com.lmsltirollcallsjtu.common.bean.canvas.SectionsOfCanvas;
import com.lmsltirollcallsjtu.common.bean.param.SignHistoryParam;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.feign.CanvasFeign;
import com.lmsltirollcallsjtu.common.properties.CanvasFeignProperties;
import com.lmsltirollcallsjtu.common.service.CourseService;
import com.lmsltirollcallsjtu.common.service.RollcallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    private CanvasFeign canvasFeign;
    @Autowired
    private CanvasFeignProperties canvasFeignProperties;
    @Autowired
    private CourseService courseService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void insertSignHistories(SignHistoryParam signHistoryParam) throws BusinessException {

        //创建点名记录
        List<SectionInfo> sectionInfos = courseService.querySectionList(signHistoryParam.getCourseCode());
            Long studentTotalOfCourse = 0L;
        List<SectionInfo> neededSections = new ArrayList<>();
        //从canvas中筛选点名的班级
        for(Long item : signHistoryParam.getSectionCodes()){
            for(SectionInfo sectionInfo : sectionInfos){
                if(sectionInfo.getSectionCode().equals(item)){
                    neededSections.add(sectionInfo);
                    studentTotalOfCourse += sectionInfo.getTotalNumOfStudents();
                }
            }
        }
        //构建signhistory对象
        SignHistory signHistory = SignHistory.builder().id(UUID.randomUUID().toString().replaceAll("\\-", ""))
                .attendancesCount(0)
                .userCode(signHistoryParam.getUserCode())
                .courseCode(signHistoryParam.getCourseCode())
                .totalStudents(studentTotalOfCourse)
                .sectionListJsonStr(JSON.toJSONString(neededSections))
                .createdBy(signHistoryParam.getUserCode().toString())
                .expAttendancesCount(studentTotalOfCourse)
                .build();
        //将signHistoryParam赋值给signHistory
        rollcallBasicService.insertSignHistories(signHistory);
        //创建点名记录之后创建学生信息记录
        List<SignRecordsBo> signRecordsBo=new ArrayList<>();
        SignRecordsBo recordsBoTemp;
        //遍历多次调用canvas获取学生信息
        for(SectionInfo item : neededSections){
            for(Student student : item.getStudentList()){
                recordsBoTemp = SignRecordsBo.builder().id(UUID.randomUUID().toString().replaceAll("\\-", ""))
                        .state("UNNORMAL")
                        .openId("null")
                        .rollcallCode(signHistory.getId())
                        .sectionName(item.getSectionName())
                        .userCode(student.getUserCode())
                        .userName(student.getUserName())
                        .createdBy(signHistoryParam.getUserCode().toString())
                        .build();
                signRecordsBo.add(recordsBoTemp);
            }
        }
        rollcallBasicService.insertStudnetsDetail(signRecordsBo);

    }
}


package com.lmsltirollcallsjtu.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.lmsltirollcallsjtu.common.base.service.RollcallBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.*;
import com.lmsltirollcallsjtu.common.bean.dto.SignScanQuartzJobDto;
import com.lmsltirollcallsjtu.common.bean.param.SignHistoryParam;
import com.lmsltirollcallsjtu.common.enums.SignInStateEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.CourseService;
import com.lmsltirollcallsjtu.common.service.RollcallService;
import com.lmsltirollcallsjtu.common.service.SignScanQuartzJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    private CourseService courseService;
    @Autowired
    private SignScanQuartzJobService signScanQuartzJobService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public String insertSignHistories(SignHistoryParam signHistoryParam) throws BusinessException, SchedulerException {

        //1.获取系统当前时间
        Date nowDate = new Date();

        //2.查询该课程下的班级详情列表（包含学生信息）
        List<SectionInfo> sectionInfos = courseService.querySectionList(signHistoryParam.getCourseCode());

        //3.根据点名班级，筛选班级详情列表(包含学生信息)，并计算这些班的总和人数
        Long studentTotalOfCourse = 0L;
        List<SectionInfo> neededSections = new ArrayList<>();
        for(Long item : signHistoryParam.getSectionCodes()){
            for(SectionInfo sectionInfo : sectionInfos){
                if(sectionInfo.getSectionCode().equals(item)){
                    neededSections.add(sectionInfo);
                    studentTotalOfCourse += sectionInfo.getTotalNumOfStudents();
                }
            }
        }

        //4.录入点名记录
        SignHistory signHistory = SignHistory.builder().id(UUID.randomUUID().toString().replaceAll("\\-", ""))
                                                       .attendancesCount(0)
                                                       .userCode(signHistoryParam.getUserCode())
                                                       .courseCode(signHistoryParam.getCourseCode())
                                                       .totalStudents(studentTotalOfCourse)
                                                       .sectionListJsonStr(JSON.toJSONString(neededSections))
                                                       .createdBy(signHistoryParam.getUserCode().toString())
                                                       .expAttendancesCount(studentTotalOfCourse).build();
        String signHistoryId = rollcallBasicService.insertSignHistories(signHistory);

        //5.录入本次点名的学生签到名册
        SignRecordsBo recordsBoTemp;
        List<SignRecordsBo> signRecordsBo=new ArrayList<>();   //本次点名的学生签到名册
        for(SectionInfo item : neededSections){
            for(Student student : item.getStudentList()){
                recordsBoTemp = SignRecordsBo.builder().id(UUID.randomUUID().toString().replaceAll("\\-", ""))
                                                       .state(SignInStateEnum.UNNORMAL.getCode())
                                                       .openId("null")
                                                       .rollcallCode(signHistoryId)
                                                       .sectionName(item.getSectionName())
                                                       .userCode(student.getUserCode())
                                                       .userName(student.getUserName())
                                                       .createdBy(signHistoryParam.getUserCode().toString()).build();
                signRecordsBo.add(recordsBoTemp);
            }
        }
        rollcallBasicService.insertStudnetsDetail(signRecordsBo);

        //6.为本次点名添加刷新signScanToken定时任务
        SignScanQuartzJobDto signScanQuartzJobDto = SignScanQuartzJobDto.builder().signScanQuartzJobLogId(UUID.randomUUID().toString().replaceAll("\\-",""))
                                                                                  .signHistoryId(signHistoryId)
                                                                                  .jobName(UUID.randomUUID().toString().replaceAll("\\-",""))
                                                                                  .jobGroup("signScanJob")
                                                                                  .triggerName(UUID.randomUUID().toString().replaceAll("\\-",""))
                                                                                  .triggerGroup("signScanTrigger")
                                                                                  .createdBy(String.valueOf(signHistoryParam.getUserCode()))
                                                                                  .createdDate(nowDate)
                                                                                  .updatedBy(String.valueOf(signHistoryParam.getUserCode()))
                                                                                  .updatedDate(nowDate).build();
        signScanQuartzJobService.addSignScanJob(signScanQuartzJobDto);

        return signHistoryId;
    }

    @Override
    public void backoutRollcall(String signHistoryId) throws SchedulerException, BusinessException {

        //1.修改点名记录的状态


        //2.关闭该次点名的定时任务
        signScanQuartzJobService.removeSignScanJob(signHistoryId);
    }
}


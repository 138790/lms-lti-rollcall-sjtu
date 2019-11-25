package com.lmsltirollcallsjtu.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.lmsltirollcallsjtu.common.base.service.CombineAttendanceBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.AttendancesCount;
import com.lmsltirollcallsjtu.common.bean.bo.Section;
import com.lmsltirollcallsjtu.common.bean.canvas.SectionsOfCanvas;
import com.lmsltirollcallsjtu.common.properties.CanvasFeignProperties;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.CombineAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CombineAttendanceServiceImpl implements CombineAttendanceService {
//    @Autowired
//    private CombineAttendanceBasicService combineAttendanceBasicService;
//    @Autowired
//    private CanvasFeignClient canvasFeignClient;
//    @Autowired
//    private CanvasFeignProperties canvasFeignProperties;
//
//    @Override
//    public AttendancesCount CombineAttendancesCountBySectionCodes(List<Long> sectionCodes) throws BusinessException {
//        if(StringUtils.isEmpty(sectionCodes)){
//            throw  BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
//        }
//        String sCodes = JSON.toJSONString(sectionCodes);
//        AttendancesCount attendancesCount = combineAttendanceBasicService.CombineAttendancesCountBySectionCodes(sCodes);
//        List<String> includeList2=new ArrayList<>();
//        includeList2.add("total_students");
//        includeList2.add("students");
//        ResponseEntity<List<SectionsOfCanvas>> sections = canvasFeignClient.getSections(canvasFeignProperties.getSupperAdminToken(), includeList2,540L);
//        List<SectionsOfCanvas> sectionsBody = sections.getBody();
//        HttpHeaders headers = sections.getHeaders();
//
////        attendancesCount.setAttendancesCount(sectionsBody.get(0).getTotal_students());
//        Long totalStudents=0L;
//        ArrayList<Section> sectionList = new ArrayList<>();
//        Section sectionTemp;
//        for(SectionsOfCanvas item:sectionsBody){
//            sectionTemp = new Section();
//            sectionTemp.setSectionCode(item.getId());
//            sectionTemp.setSectionName(item.getName());
//            sectionList.add(sectionTemp);
//            totalStudents+=item.getTotal_students();
//        }
//        attendancesCount.setSection(sectionList);
//        attendancesCount.setTotalStudents(totalStudents);
//        return attendancesCount;
//    }
}

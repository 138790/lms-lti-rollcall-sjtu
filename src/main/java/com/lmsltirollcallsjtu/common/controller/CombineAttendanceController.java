package com.lmsltirollcallsjtu.common.controller;

import com.alibaba.fastjson.JSON;
import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.bean.bo.AttendancesCount;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.CombineAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @author huyong
 * @createdDate 2019.11.14
 * @Description 合并签到
 * @param List<Long> sectionCodes
 * @return ResultInfo<AttendancesCount>
 */
@RestController
@RequestMapping("/combine")
public class CombineAttendanceController {
//    @Autowired
//    private CombineAttendanceService combineAttendanceService;
//    @UserLoginToken
//    @RequestMapping("/attendances")
//    public ResultInfo<AttendancesCount> doCombineAttendances(@RequestParam("sectionCodes") List<Long> sectionCodes,@RequestParam("userCode")Long userCode) throws BusinessException {
//  在service层处理      String  sCodes = JSON.toJSONString(sectionCodes);
//        AttendancesCount attendancesCount = combineAttendanceService.CombineAttendancesCountBySectionCodes(sectionCodes);
//        ResultInfo<AttendancesCount> resultInfo = ResultInfo.success(attendancesCount);
//        return resultInfo;
//    }

}

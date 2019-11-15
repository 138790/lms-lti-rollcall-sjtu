package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.bean.bo.SignRecordInfo;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsOfCourse;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.SignRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class SignRecordsController {
    @Autowired
    private SignRecordsService signRecordsService;
    //学生查询签到历史列表
    @RequestMapping("/sign")
    public ResultInfo<List<SignRecordsDto>> doFindSignHistoryListsOfCourse(@RequestBody SignRecordsOfCourse signRecordsOfCourse) throws BusinessException {
        List<SignRecordsDto> signRecordsDto = signRecordsService.findSignHistoryByUserCodeAndCourseCode(signRecordsOfCourse);
        ResultInfo<List<SignRecordsDto>> resultInfo = ResultInfo.success(signRecordsDto);
        return resultInfo;
    }
    //学生查询一次点名的签到详情
    @RequestMapping("/condition")
    public ResultInfo<SignRecordsDto> doFindSignCondition(@RequestBody SignRecordInfo signRecordInfo) throws BusinessException {
       SignRecordsDto signRecordsDto = signRecordsService.findSignConditionByRollcallCode(signRecordInfo);
        ResultInfo<SignRecordsDto> resultInfo = ResultInfo.success(signRecordsDto);
        return resultInfo;
    }
}

package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordInfo;
import com.lmsltirollcallsjtu.common.bean.param.SignRecordsOfCourseParam;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.SignRecordsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(value = "/rollcall API", tags = "学生查看所选某门课程的签到历史")
@RestController
@RequestMapping("/records")
public class SignRecordsController {
    @Autowired
    private SignRecordsService signRecordsService;
    //学生查询签到历史列表
    @UserLoginToken
    @ApiOperation(value="查询所选某门课程的签到历史列表" ,notes = "查询所选某门课程的签到历史列表")
    @ApiImplicitParam(name = "userCode",value = "用户编号", paramType = "query", dataType = "Long")
    @GetMapping("/sign")
    public ResultInfo<List<SignRecordsDto>> querySignHistoryListsOfCourse(SignRecordsOfCourseParam signRecordsOfCourseParam, @RequestParam("userCode") Long userCode) throws BusinessException {
        signRecordsOfCourseParam.setUserCode(userCode);
        List<SignRecordsDto> signRecordsDto = signRecordsService.findSignHistoryByUserCodeAndCourseCode(signRecordsOfCourseParam);
        ResultInfo<List<SignRecordsDto>> resultInfo = ResultInfo.success(signRecordsDto);
        return resultInfo;
    }
    //学生查询一次点名的签到详情
    @UserLoginToken
    @GetMapping("/condition")
    public ResultInfo<SignRecordsDto> querySignCondition( SignRecordInfo signRecordInfo,@RequestParam("userCode")Long userCode) throws BusinessException {
        signRecordInfo.setUserCode(userCode);
        SignRecordsDto signRecordsDto = signRecordsService.findSignConditionByRollcallCode(signRecordInfo);
        ResultInfo<SignRecordsDto> resultInfo = ResultInfo.success(signRecordsDto);
        return resultInfo;
    }
}

package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.bean.param.SignRecordsOfCourseParam;
import com.lmsltirollcallsjtu.common.bean.vo.PagedVo;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.SignRecordsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/sign")
    public ResultInfo<PagedVo<List<SignRecordsDto>>> querySignHistoryListsOfCourse(@Validated SignRecordsOfCourseParam signRecordsOfCourseParam, @ApiParam(hidden = true) @RequestParam("userCode") Long userCode) throws BusinessException {
        signRecordsOfCourseParam.setUserCode(userCode);
        PagedVo<List<SignRecordsDto>>signRecordsDto = signRecordsService.findSignHistoryByUserCodeAndCourseCode(signRecordsOfCourseParam);
        ResultInfo<PagedVo<List<SignRecordsDto>>> resultInfo = ResultInfo.success(signRecordsDto);
        return resultInfo;
    }

}

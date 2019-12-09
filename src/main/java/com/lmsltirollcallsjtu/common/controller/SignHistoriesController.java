package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.bean.bo.*;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
import com.lmsltirollcallsjtu.common.bean.param.QuerySignDetailsListParam;
import com.lmsltirollcallsjtu.common.bean.param.QuerySignHistoryListParam;
import com.lmsltirollcallsjtu.common.bean.vo.PagedVo;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.SignHistoriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author huyong
 * @date 2019-11-2
 * @description 查看签到历史情况controller
 */
@Api(value = "/rollcalls API ",tags = "签到历史相关接口")
@RestController
@RequestMapping("/rollcalls")
public class SignHistoriesController {

    @Autowired
    private SignHistoriesService signHistoriesService;


    @UserLoginToken
    @ApiOperation(value = "查询某一次点名签到列表", notes = "查询某一次点名签到列表")
    @ApiImplicitParam(name = "id", value = "点名编号", paramType = "path", dataType = "String")
    @GetMapping("/scan")
    public ResultInfo<PagedVo<List<SignRecordsInfo>>> querySignHistories(@Validated QuerySignDetailsListParam querySignDetailsListParam) throws BusinessException {
        PagedVo<List<SignRecordsInfo>>  signRecordsInfo = signHistoriesService.findSignHistoryByRollcallId(querySignDetailsListParam);
        ResultInfo<PagedVo<List<SignRecordsInfo>>> resultInfo = ResultInfo.success(signRecordsInfo);
        return resultInfo;
    }

    //查询学生签到历史列表(分页)
    @UserLoginToken
    @ApiOperation(value = "查询学生签到历史列表", notes = "查询学生签到历史列表")
    @GetMapping("/querySignHistoryList")
    public ResultInfo<PagedVo<List<SignHistoryDto>>> querySignHistoryList(@Validated QuerySignHistoryListParam querySignHistoryListParam) throws BusinessException {
        PagedVo<List<SignHistoryDto>> signHistoryInfo = signHistoriesService.findSignHistoryListByCourseCode(querySignHistoryListParam);
        ResultInfo<PagedVo<List<SignHistoryDto>>> resultInfo = ResultInfo.success(signHistoryInfo);
        return resultInfo;
    }

    //删除签到记录
    @UserLoginToken
    @ApiOperation(value = "删除签到记录", notes = "删除签到记录")
    @ApiImplicitParam(name = "id", value = "点名编号", paramType = "path", dataType = "String")
    @GetMapping("/deleteSignHistory/{id}")
    public ResultInfo<String> doDeleteSignHisory(@PathVariable String id) throws BusinessException {
        signHistoriesService.deleteSignHistory(id);
        return ResultInfo.success("删除签到记录成功");
    }
}

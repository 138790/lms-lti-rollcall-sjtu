package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.bean.bo.*;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
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
 * @Description:查看签到历史情况controller
 */
@Api(value = "/rollcalls API ",tags = "查询签到历史相关接口")
@RestController
@RequestMapping("/rollcalls")
public class SignHistoriesController {
    @Autowired
    private SignHistoriesService signHistoriesService;
    /*@Autowired
    private CanvasService canvasService;*/
    //查询一次点名的签到详情
    @ApiOperation(value = "查询某一次点名签到列表", notes = "查询某一次点名签到列表")
    @ApiImplicitParam(name = "id",value = "点名id", required = true, paramType = "path", dataType = "String")
    @GetMapping("scan/{id}")
    public ResultInfo<List<SignHistoryInfo>> querySignHistories(@PathVariable("id") String id) throws BusinessException {
        List<SignHistoryInfo>  signHistoryInfo = signHistoriesService.findSignHistoryByRollcallId(id);
        ResultInfo<List<SignHistoryInfo>> resultInfo = ResultInfo.success(signHistoryInfo);
        return resultInfo;
    }

    //查询学生签到历史列表(分页)
    @ApiOperation(value = "查询学生签到历史列表", notes = "查询学生签到历史列表")
    @PostMapping("/querySignHistoryList")
    public ResultInfo<PagedVo<List<SignHistoryDto>>> querySignHistoryList(@RequestBody @Validated QuerySignHistoryListParam querySignHistoryListParam) throws BusinessException {
        PagedVo<List<SignHistoryDto>> signHistoryInfo = signHistoriesService.findSignHistoryListByCourseCode(querySignHistoryListParam);
        ResultInfo<PagedVo<List<SignHistoryDto>>> resultInfo = ResultInfo.success(signHistoryInfo);
        return resultInfo;
    }

}

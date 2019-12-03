package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.bean.param.SignHistoryParam;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.RollcallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(value = "/rollcall API", tags = "点名相关接口")
@RestController
@RequestMapping("/rollcall")
public class RollcallController {

    @Autowired
    private RollcallService rollcallService;

    /**
     * @author huyong
     * @createdDate 2019.11.14
     * @Description 发起点名
     * @parameter signHistoryParam
     * @return ResultInfo<String>
     */
    @UserLoginToken
    @ApiOperation(value="发起点名" ,notes = "发起点名")
    @ApiImplicitParam(name = "userCode",value = "用户编号", paramType = "query", dataType = "Long")
    @PostMapping("/insertSignHistories")
    public ResultInfo<String> insertSignHistories(@RequestBody @Validated SignHistoryParam signHistoryParam, @RequestParam("userCode") Long userCode) throws BusinessException, SchedulerException {

        //1.设置用户编号参数
        signHistoryParam.setUserCode(userCode);

        //2.录入点名记录、学生签到名册，并设置刷新signScanToken定时任务
        rollcallService.insertSignHistories(signHistoryParam);

        //3.响应信息
        return ResultInfo.success("发起点名成功");
    }

    /**
     * @author huyong
     * @createdDate 2019.12.03
     * @Description 关闭点名
     * @parameter signHistoryId、userCode
     * @return ResultInfo<String>
     */
    @UserLoginToken
    @ApiOperation(value="关闭点名" ,notes = "关闭点名")
    @ApiImplicitParam(name = "userCode",value = "用户编号", paramType = "query", dataType = "Long")
    @DeleteMapping("/{signHistoryId}")
    public ResultInfo<String> backoutRollcall(@PathVariable String signHistoryId, @RequestParam("userCode") Long userCode) throws BusinessException, SchedulerException {

        //1.修改点名记录的状态，关闭该次点名的定时任务
        rollcallService.backoutRollcall(signHistoryId);

        //2.响应信息
        return ResultInfo.success();
    }

}

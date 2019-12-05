package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.bean.param.UpdateSignHistoryStateParam;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.ScanSignServcie;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author huyong
 * @date 2019-11-2
 * @description 学生签到controller
 */
@Api(value = "/scanSign API", tags = "扫码签到相关接口")
@RestController
@RequestMapping("/scan")
public class ScanSignController {

    @Autowired
    private ScanSignServcie scanSignServcie;


    /**
     * @author huyong
     * @date 2019-11-2
     * @description 学生扫码签到并修改签到状态
     */
    @UserLoginToken
    @ApiOperation(value="学生扫码签到并修改签到状态" ,notes = "学生扫码签到并修改签到状态")
    @ApiImplicitParam(name = "userCode",value = "用户编号", paramType = "query", dataType = "Long")
    @PostMapping("/sign")
    public ResultInfo<String> doUpdateStateByRecordId(@RequestBody @Validated UpdateSignHistoryStateParam updateSignHistoryStateParam, @RequestParam("userCode") Long userCode) throws BusinessException, InterruptedException {

        //1.设置用户编号参数
        updateSignHistoryStateParam.setUserCode(userCode);

        //2.更新该次点名的签到状态
        scanSignServcie.scanUpdateState(updateSignHistoryStateParam);

        //3.响应结果
        return ResultInfo.success("扫码签到成功");
    }
}

package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.base.service.ScanSignBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.UserStateInfo;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.ScanSignServcie;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
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

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;


    /**
     * @author huyong
     * @date 2019-11-2
     * @description 学生扫码签到并修改签到状态
     */
    @UserLoginToken
    @ApiOperation(value="学生扫码签到并修改签到状态" ,notes = "学生扫码签到并修改签到状态")
    @ApiImplicitParam(name = "userCode",value = "用户编号", paramType = "query", dataType = "Long")
    @GetMapping("/sign/{signScanToken}")
    public ResultInfo<String> doUpdateStateByRecordId(@PathVariable String signScanToken) throws BusinessException, InterruptedException {

        //1.更新该次点名的签到状态
        UserStateInfo userStateInfo = scanSignServcie.scanUpdateState(signScanToken);

        //2.推送签到用户的姓名到客户端
        simpMessageSendingOperations.convertAndSend("/topic/users/" + userStateInfo.getSignHistoryId(), userStateInfo.getUserName());

        //3.响应结果
        return ResultInfo.success("扫码签到成功");
    }
}

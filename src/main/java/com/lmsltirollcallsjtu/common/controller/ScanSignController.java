package com.lmsltirollcallsjtu.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.bean.bo.UserStateInfo;
import com.lmsltirollcallsjtu.common.bean.param.SignInParam;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.enums.MessageQueueEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.ScanSignServcie;
import com.lmsltirollcallsjtu.common.utils.ExecutionContext;
import com.lmsltirollcallsjtu.common.utils.SendMessageUtil;
import com.lmsltirollcallsjtu.common.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @ApiImplicitParam(name = "signScanToken",value = "签到二维码token", paramType = "path", dataType = "String")
    @GetMapping("/sign/{signScanToken}")
    public ResultInfo<String> doUpdateStateByRecordId(@PathVariable String signScanToken, @ApiParam(hidden = true) @RequestParam("userCode") Long userCode) throws BusinessException, InterruptedException {

        //1.获取系统当前时间、用户编号
        Date nowDate = new Date();

        //2.验证signScanToken
        String signHistoryId = TokenUtil.verifySignScanToken(signScanToken);

        //3.发送签到消息到指定队列
        String messageId = MessageQueueEnum.SIGN_IN_QUEUE.getExchangeName()+":"+MessageQueueEnum.SIGN_IN_QUEUE.getRoutingKey()+":"+signHistoryId+":"+userCode+":"+nowDate;
        SignInParam signInParam = SignInParam.builder().signHistoryId(signHistoryId).userCode(userCode).signInDate(nowDate).build();
        SendMessageUtil.sendMsg(messageId, JSONObject.toJSONString(signInParam));
        
        //4.响应结果
        return ResultInfo.success("扫码签到成功");
    }
}

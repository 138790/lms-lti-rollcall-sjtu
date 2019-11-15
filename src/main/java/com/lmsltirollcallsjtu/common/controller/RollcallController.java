package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.bean.bo.SignHistory;
import com.lmsltirollcallsjtu.common.bean.bo.UserInfo;
import com.lmsltirollcallsjtu.common.bean.param.SignHistoryParam;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.service.RollcallService;
import com.lmsltirollcallsjtu.common.utils.SecretUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author huyong
 * @createdDate 2019.11.14
 * @Description 插入点名记录和学生信息
 * @param signHistory
 * @return ResultInfo<String>
 */
@RestController
@RequestMapping("/rollcall")
public class RollcallController {
    @Autowired
    private RollcallService rollcallService;
    @RequestMapping("/insert")
    public ResultInfo<String> doInsertSignHistories(@RequestBody SignHistoryParam signHistoryParam){
        rollcallService.insertSignHistories(signHistoryParam);
        ResultInfo<String> resultInfo = ResultInfo.success("发起点名成功");
        return resultInfo;
    }

}

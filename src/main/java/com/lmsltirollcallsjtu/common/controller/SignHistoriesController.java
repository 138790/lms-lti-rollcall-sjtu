package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.bean.po.SignHistories;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.service.SignHistoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rollcalls")
public class SignHistoriesController {
    @Autowired
    private SignHistoriesService signHistoriesService;
    @RequestMapping("/{rollcallId}")
    public ResultInfo<SignHistories> selectSignHistories(@PathVariable Long rollcallId){
        SignHistories signHistories = signHistoriesService.selectSignHistoryByRollcallId(rollcallId);
        ResultInfo<SignHistories> resultInfo = ResultInfo.success(signHistories);
        return resultInfo;
    }
}

package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.bean.po.SignRecords;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.service.ScanSignServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scan")
public class ScanSignController {
    @Autowired
    private ScanSignServcie scanSignServcie;

    @RequestMapping("/sign")
    public ResultInfo<String> doScanSign( @RequestBody @Validated  SignRecords signRecords){
        scanSignServcie.insertObject(signRecords);
        ResultInfo<String> success = ResultInfo.success("签到成功");
        return success;
    }
}

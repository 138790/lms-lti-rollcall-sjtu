package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.service.ScanSignServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author huyong
 * @date 2019-11-2
 * @Description:学生签到controller
 */
@RestController
@RequestMapping("/scan")
public class ScanSignController {
    @Autowired
    private ScanSignServcie scanSignServcie;

    @RequestMapping("/sign")
    public ResultInfo<String> doFindScanSign( @RequestBody  SignRecordsDto signRecordsDto){
        scanSignServcie.scanUpdateState(signRecordsDto);
        ResultInfo<String> resultInfo = ResultInfo.success("签到正常");
        return resultInfo;
    }
}

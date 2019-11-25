package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordDto;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.ScanSignServcie;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huyong
 * @date 2019-11-2
 * @Description:学生签到controller
 */
@Api(value = "/scanSign API", tags = "扫码签到相关接口")
@RestController
@RequestMapping("/scan")
public class ScanSignController {
    @Autowired
    private ScanSignServcie scanSignServcie;
    @UserLoginToken
    @ApiOperation(value="学生扫码签到并修改签到状态" ,notes = "学生扫码签到并修改签到状态")
    @PostMapping("/sign")
    public ResultInfo<String> doUpdateStateByRecordId(@RequestBody SignRecordDto signRecordDto, @RequestParam("userCode") Long userCode) throws BusinessException {
        scanSignServcie.scanUpdateState(signRecordDto);
        ResultInfo<String> resultInfo = ResultInfo.success("扫码签到成功");
        return resultInfo;
    }
}

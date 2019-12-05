package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.bean.param.IdsParam;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.CombineAttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author huyong
 * @createdDate 2019.11.14
 * @description 合并点名记录相关接口
 */
@Api(value = "/combine API", tags = "合并点名记录相关接口")
@RestController
@RequestMapping("/combine")
public class CombineAttendanceController {

    @Autowired
    private CombineAttendanceService combineAttendanceService;

    @UserLoginToken
    @ApiOperation(value="合并点名记录和签到明细记录" ,notes = "合并点名记录和签到明细记录")
    @PostMapping("/signHistories")
    public ResultInfo<String> doCombineSignHistories(@RequestBody @Validated IdsParam idsParam, @RequestParam("userCode") Long userCode) throws BusinessException {
        idsParam.setUserCode(userCode);
        combineAttendanceService.combineInsertSignHistoryBySignHistory(idsParam);
        ResultInfo<String> resultInfo = ResultInfo.success("合并签到历史成功");
        return resultInfo;
    }

}
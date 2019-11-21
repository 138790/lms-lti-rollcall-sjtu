package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecords;
import com.lmsltirollcallsjtu.common.bean.bo.UserStates;
import com.lmsltirollcallsjtu.common.bean.dto.DictionaryDto;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.UpdateStateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@Api(value = "/rollcall API", tags = "修改签到状态相关接口")
@RestController
@RequestMapping("/update")
public class UpdateStateController {
    @Autowired
    private UpdateStateService updateStateService;
    @UserLoginToken
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictCode", value = "字典编号", paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "rollcallCode", value = "点名编号", paramType = "path", dataType = "String")
    })
    @PostMapping("/state/{rollcallCode}/{dictCode}")
    public ResultInfo<String> doUpdateState(@RequestBody UserStates userStates,@PathVariable("dictCode")String dictCode,@PathVariable("rollcallCode") String rollcallCode) throws BusinessException {
        userStates.setRollcallCode(rollcallCode);
        updateStateService.updateUserStateByUserStates(dictCode,userStates);
        ResultInfo<String> resultInfo = ResultInfo.success("修改状态成功");
        return resultInfo;
    }

}

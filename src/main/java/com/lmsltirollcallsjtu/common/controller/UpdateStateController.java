package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.bean.bo.UserStates;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.UpdateStateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(value = "/rollcall API", tags = "修改签到状态相关接口")
@RestController
@RequestMapping("/update")
public class UpdateStateController {
    @Autowired
    private UpdateStateService updateStateService;

    @UserLoginToken
    @ApiOperation(value="修改学生签到状态" ,notes = "修改学生签到状态")
    @PostMapping("/doUpdateState")
    public ResultInfo<String> doUpdateState(@RequestBody @Validated UserStates userStates) throws BusinessException {

        updateStateService.updateUserStateByUserStates(userStates);
        ResultInfo<String> resultInfo = ResultInfo.success("修改状态成功");
        return resultInfo;
    }

}

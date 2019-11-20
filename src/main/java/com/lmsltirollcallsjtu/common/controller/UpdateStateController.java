package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecords;
import com.lmsltirollcallsjtu.common.bean.bo.UserStates;
import com.lmsltirollcallsjtu.common.bean.dto.DictionaryDto;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.UpdateStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/update")
public class UpdateStateController {
    @Autowired
    private UpdateStateService updateStateService;
    @UserLoginToken
    @PostMapping("/state/{rollcallCode}")
    public ResultInfo<String> doUpdateState(@RequestBody List<UserStates> userStateList, @RequestParam("userCode") Long userCode,@PathVariable String rollcallCode) throws BusinessException {
        List<Long> userCodes = userStateList.stream().map(item -> item.getUserCode()).collect(Collectors.toList());
        userCodes.add(userCode);
        updateStateService.updateUserStateByUserStates(rollcallCode,userStateList);
        ResultInfo<String> resultInfo = ResultInfo.success("修改状态成功");
        return resultInfo;
    }

}

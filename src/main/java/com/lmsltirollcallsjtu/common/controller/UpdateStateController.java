package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecords;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.service.UpdateStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/update")
public class UpdateStateController {
    @Autowired
    private UpdateStateService updateStateService;
    @UserLoginToken
    @RequestMapping("/state")
    public ResultInfo<String> doUpdateState(@RequestBody SignRecords signRecords, @RequestParam("userCode") Long userCode) {
        signRecords.setUserCode(userCode);
        updateStateService.updateUserStateBySignRecordsDto(signRecords);
        ResultInfo<String> resultInfo = ResultInfo.success("修改成功");
        return resultInfo;
    }
}

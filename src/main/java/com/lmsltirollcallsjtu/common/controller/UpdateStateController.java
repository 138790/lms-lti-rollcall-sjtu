package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.bean.bo.SignRecords;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.service.UpdateStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/update")
public class UpdateStateController {
    @Autowired
    private UpdateStateService updateStateService;

    @RequestMapping("/state")
    public ResultInfo<String> doUpdateState(@RequestBody SignRecords signRecords) {
        updateStateService.updateUserStateBySignRecordsDto(signRecords);
        ResultInfo<String> resultInfo = ResultInfo.success("修改成功");
        return resultInfo;
    }
}

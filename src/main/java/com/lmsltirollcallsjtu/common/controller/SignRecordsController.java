package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.SignRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/records")
public class SignRecordsController {
    @Autowired
    private SignRecordsService signRecordsService;
    //学生查询签到历史列表
    @RequestMapping("/sign/{userCode}")
    public ResultInfo<List<SignRecordsDto>> doFindSignHistoryLists(@PathVariable Long userCode) throws BusinessException {
        List<SignRecordsDto> signRecordsDto = signRecordsService.findSignHistoryByUserCode(userCode);
        ResultInfo<List<SignRecordsDto>> resultInfo = ResultInfo.success(signRecordsDto);
        return resultInfo;
    }
    @RequestMapping("condition/{rollcallCode}")
    public ResultInfo<SignRecordsDto> doFindSignCondition(@PathVariable String rollcallCode) throws BusinessException {
        SignRecordsDto signRecordsDto = signRecordsService.findSignConditionByRollcallCode(rollcallCode);
        ResultInfo<SignRecordsDto> resultInfo = ResultInfo.success(signRecordsDto);
        return resultInfo;
    }
}

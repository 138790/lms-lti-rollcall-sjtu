package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.bean.bo.SignHistoryInfo;
import com.lmsltirollcallsjtu.common.bean.bo.UserCourseInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.SignHistoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huyong
 * @date 2019-11-2
 * @Description:查看签到历史情况controller
 */
@RestController
@RequestMapping("/rollcalls")
public class SignHistoriesController {
    @Autowired
    private SignHistoriesService signHistoriesService;
    //查询一次点名的签到详情
    @RequestMapping("/{id}")
    public ResultInfo<SignHistoryInfo> doFindSignHistories(@PathVariable("id") String id) throws BusinessException {
        SignHistoryInfo  signHistoryInfo = signHistoriesService.findSignHistoryByRollcallId(id);
        ResultInfo< SignHistoryInfo> resultInfo = ResultInfo.success(signHistoryInfo);
        return resultInfo;
    }

    //查询学生签到历史列表
    @RequestMapping("/sign")
    public ResultInfo<List<SignHistoryDto>> doFindSignHistoryList(@RequestBody UserCourseInfo userCourseInfo) throws BusinessException {
        List<SignHistoryDto> signHistoryInfo = signHistoriesService.findSignHistoryListByCourseIdAndUserId(userCourseInfo);
        ResultInfo<List<SignHistoryDto>> resultInfo = ResultInfo.success(signHistoryInfo);
        return resultInfo;
    }

  /*  //学生查询签到历史
    @RequestMapping("/{userId}")
    public ResultInfo<List<SignRecordsDto>> doFindSignHistoryLists(@PathVariable Long userId){
        List<SignRecordsDto> signRecords = signHistoriesService.findSignHistoryByUserId(userId);
        ResultInfo<List<SignRecordsDto>> resultInfo = ResultInfo.success(signRecords);
        return resultInfo;
    }*/
}

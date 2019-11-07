package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.bean.bo.*;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.CanvasService;
import com.lmsltirollcallsjtu.common.service.SignHistoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
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
    @Autowired
    private CanvasService canvasService;
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
    @RequestMapping("/sections/{courseId}")
    public ResultInfo<ResponseEntity<List<Sections>>> doFindSections(@RequestHeader("Authorization")String bearerToken,@PathVariable  Long courseId){
        ResponseEntity<List<Sections>> sections = canvasService.getSections(bearerToken,courseId);
        ResultInfo<ResponseEntity<List<Sections>>> resultInfo = ResultInfo.success(sections);
        return resultInfo;
    }
    @RequestMapping("/sections/{courseId}/{sectionId}")
    public ResultInfo<ResponseEntity<List<Sections>>> doFindTotalStudents(@RequestHeader("Authorization")String bearerToken,@PathVariable Long courseId,@PathVariable Long sectionId ){
        ResponseEntity<List<Sections>> sections = canvasService.getSectionDetail(bearerToken,courseId,sectionId);
        ResultInfo<ResponseEntity<List<Sections>>> resultInfo = ResultInfo.success(sections);
        return resultInfo;
    }
}

package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.bean.bo.*;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.SignHistoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    /*@Autowired
    private CanvasService canvasService;*/
    //查询一次点名的签到详情
    @RequestMapping("/{id}")
    public ResultInfo<List<SignHistoryInfo>> doFindSignHistories(@PathVariable("id") String id) throws BusinessException {
        List<SignHistoryInfo>  signHistoryInfo = signHistoriesService.findSignHistoryByRollcallId(id);
        ResultInfo<List<SignHistoryInfo>> resultInfo = ResultInfo.success(signHistoryInfo);
        return resultInfo;
    }

    //查询学生签到历史列表
    @RequestMapping("/sign")
    public ResultInfo<List<SignHistoryDto>> doFindSignHistoryList(@RequestParam("courseCode") Integer courseCode) throws BusinessException {
        List<SignHistoryDto> signHistoryInfo = signHistoriesService.findSignHistoryListByCourseCode(courseCode);
        ResultInfo<List<SignHistoryDto>> resultInfo = ResultInfo.success(signHistoryInfo);
        return resultInfo;
    }
   /* @RequestMapping("/sections/{courseId}")
    public ResultInfo<List<Sections>> doFindSections(@RequestHeader("Authorization")String bearerToken,
                                                                     @PathVariable  Long courseId){
        List<Sections> sections =  canvasService.getSections(bearerToken,courseId);
        ResultInfo<List<Sections>> resultInfo = ResultInfo.success(sections);
        return resultInfo;
    }
    @RequestMapping("/sections/{courseId}/{sectionId}")
    public ResultInfo<List<Sections>> doFindTotalStudents(@RequestHeader(name = "Authorization",required = true)String bearerToken,
                                                                          @PathVariable("courseId") Long courseId,
                                                                          @PathVariable("id") Long id,
                                                                          @RequestParam("include") Long includeTotalStudents,
                                                                          @RequestParam("include") String includeStringName){
        List<Sections> sections = canvasService.getSectionDetail(bearerToken,courseId,id,includeTotalStudents,includeStringName);
        ResultInfo<List<Sections>> resultInfo = ResultInfo.success(sections);
        return resultInfo;
    }*/
}

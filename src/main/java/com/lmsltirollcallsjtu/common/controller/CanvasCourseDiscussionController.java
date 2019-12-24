package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.bean.canvas.CourseDiscussionTopicOfCanvas;
import com.lmsltirollcallsjtu.common.bean.canvas.DiscussionDetailsEntriesOfCanvas;
import com.lmsltirollcallsjtu.common.bean.param.CourseTopicParam;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.CanvasDiscussionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Api(value = "/courseDiscussion API", tags = "课程讨论相关接口")
@RestController
@RequestMapping("/query")
public class CanvasCourseDiscussionController {
    @Autowired
    private CanvasDiscussionService canvasDiscussionService;

    @ApiOperation(value = "查询课程讨论列表", notes = "查询课程讨论列表")
    @ApiImplicitParam(name = "courseCode",value = "课程编号", required = true, paramType = "path", dataType = "Long")
    @UserLoginToken
    @GetMapping("/discussionTopicList/{courseCode}")
    public ResultInfo<List<CourseDiscussionTopicOfCanvas>> queryDiscussionTopicList(@PathVariable("courseCode") @NotBlank Long courseCode) throws BusinessException {
        List<CourseDiscussionTopicOfCanvas> courseDiscussionTopicOfCanvas = canvasDiscussionService.queryDiscussionTopicListOfCanvas(courseCode);
        ResultInfo<List<CourseDiscussionTopicOfCanvas>> resultInfo = ResultInfo.success(courseDiscussionTopicOfCanvas);
        return resultInfo;
    }

    @ApiOperation(value = "查询课程某一讨论主题详情及讨论回复列表", notes = "查询课程某一讨论主题详情及讨论回复列表")
    @UserLoginToken
    @PostMapping("/discussionDetailsEntryList")
    public ResultInfo<DiscussionDetailsEntriesOfCanvas> queryDiscussionDetailsEntries(@RequestBody @Validated CourseTopicParam courseTopicParam) throws BusinessException {
        DiscussionDetailsEntriesOfCanvas discussionDetailsEntriesOfCanvas = canvasDiscussionService.queryDiscussionDetailsEntriesOfCanvas(courseTopicParam);
        ResultInfo<DiscussionDetailsEntriesOfCanvas> resultInfo = ResultInfo.success(discussionDetailsEntriesOfCanvas);
        return resultInfo;
    }
}

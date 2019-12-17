package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.bean.bo.SectionInfo;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.CanvasCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Api(value = "/course API", tags = "课程相关接口")
@RestController
@RequestMapping("/course")
public class CanvasCourseController {

    @Autowired
    private CanvasCourseService canvasCourseService;

    /**
     * @author wangzhijun
     * @createdDate 2019.11.06
     * @Description 根据用户编号查询课程列表（分页）
     * @param queryCoursePagedListParam
     * @return ResultInfo<PagedVo<List<CourseInfo>>>
     */
//    @ApiOperation(value = "查询用户的课程列表（分页）", notes = "查询用户的课程分页列表（分页）")
//    @UserLoginToken
//    @GetMapping("/queryPagedList")
//    public ResultInfo<PagedVo<List<CourseInfo>>> queryPagedList(@Validated QueryCoursePagedListParam queryCoursePagedListParam) throws BusinessException {
//        PagedVo<List<CourseInfo>> coursePagedInfo = canvasCourseService.queryPagedList(queryCoursePagedListParam);
//        System.out.println("===============================queryPagedList分页查询成功================================");
//        System.out.println(coursePagedInfo.toString());
//        return ResultInfo.success(coursePagedInfo);
//    }

    /**
     * @author wangzhijun
     * @createdDate 2019.11.06
     * @Description 根据课程编号查询班级列表（分页）
     * @param courseCode
     * @return ResultInfo<List<SectionInfo>>
     */
    @ApiOperation(value = "查询课程的班级列表", notes = "查询课程的班级列表")
    @ApiImplicitParam(name = "courseCode",value = "课程编号", required = true, paramType = "path", dataType = "Long")
    @UserLoginToken
    @GetMapping("/{courseCode}/querySectionList")
    public ResultInfo<List<SectionInfo>> querySectionList(@PathVariable("courseCode") Long courseCode) throws BusinessException {
        List<SectionInfo> sectionInfoList = canvasCourseService.querySectionList(courseCode);
        System.out.println("===============================querySectionList查询分页成功================================");
        System.out.println(sectionInfoList.toString());
        return ResultInfo.success(sectionInfoList);
    }

}



package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.bo.CourseInfo;
import com.lmsltirollcallsjtu.common.bean.bo.SectionInfo;
import com.lmsltirollcallsjtu.common.bean.param.QueryCoursePagedListParam;
import com.lmsltirollcallsjtu.common.bean.vo.PagedVo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import java.util.List;

/**
 * @author wangzhijun
 * @createdDate 2019.11.06
 */
public interface CourseService {

    /**
     * @author wangzhijun
     * @createdDate 2019.11.06
     * @Description 查询课程列表（分页）
     * @param queryCoursePagedListParam
     * @return  PagedVo<List<CourseInfo>>
     */
    PagedVo<List<CourseInfo>> queryPagedList(QueryCoursePagedListParam queryCoursePagedListParam) throws BusinessException;

    /**
     * @author wangzhijun
     * @createdDate 2019.11.06
     * @Description 根据课程编号查询班级列表（下拉列表）
     * @param courseCode
     * @return List<SectionInfo>
     */
    List<SectionInfo> querySectionList(Long courseCode) throws BusinessException;
}

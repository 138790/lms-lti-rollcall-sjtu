package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.bean.bo.*;
import com.lmsltirollcallsjtu.common.bean.canvas.*;
import com.lmsltirollcallsjtu.common.bean.param.QueryCoursePagedListParam;
import com.lmsltirollcallsjtu.common.bean.vo.PagedVo;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.enums.RoleEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.feign.CanvasFeign;
import com.lmsltirollcallsjtu.common.properties.CanvasFeignProperties;
import com.lmsltirollcallsjtu.common.service.CanvasCourseService;
import com.lmsltirollcallsjtu.common.utils.PagingUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author wangzhijun
 * @createdDate 2019.11.06
 */
@Service
public class CanvasCourseServiceImpl implements CanvasCourseService {

    @Autowired
    private CanvasFeign canvasFeign;
    @Autowired
    private CanvasFeignProperties canvasFeignProperties;
    @Autowired
    private PagingUtil pagingUtil;

    /**
     * @author wangzhijun
     * @createdDate 2019.11.06
     * @Description 查询课程列表（分页）
     * @param queryCoursePagedListParam
     * @return  PagedVo<List<CourseInfo>>
     */
    @Override
    public PagedVo<List<CourseInfo>> queryPagedList(QueryCoursePagedListParam queryCoursePagedListParam) throws BusinessException {

        //1.查询用户的所有课程颜色数据
        CourseColorsOfCanvasUser courseColorsOfCanvasUser = canvasFeign.queryCourseColorsOfUser(canvasFeignProperties.getSupperAdminToken(), queryCoursePagedListParam.getUserCode());
        Map<String, String> courseColorsMap = courseColorsOfCanvasUser.getCustom_colors();

        //2.查询用户的课程列表
        List<CourseInfoOfCanvas> courseInfoListOfCanvas = queryCourseListByUserCode(queryCoursePagedListParam.getUserCode());

        //3.如果模糊的课程名称不为空，则筛选出名称匹配的课程列表
        if(StringUtils.isNotBlank(queryCoursePagedListParam.getVagueCourseName())){
            courseInfoListOfCanvas = courseInfoListOfCanvas.stream()
                    .filter(courseInfoOfCanvas -> courseInfoOfCanvas.getName().contains(queryCoursePagedListParam.getVagueCourseName()))
                    .collect(Collectors.toList());
        }

        //4.对课程列表数据做分页处理
        PagedVo<List<CourseInfoOfCanvas>> coursePagedInfoOfCanvas = pagingUtil.getCurrentPagedInfo(queryCoursePagedListParam.getPageNum(), queryCoursePagedListParam.getPageSize(), courseInfoListOfCanvas);

        //5.封装当前页的数据出参，并返回
        List<CourseInfo> courseInfoListOfCurrentPage = new ArrayList<CourseInfo>();
        coursePagedInfoOfCanvas.getDataList().stream().forEach(item ->{
            //5.1获取该课程的颜色
            String courseColor = courseColorsMap.get("course_" + item.getId());

            //5.2获取该课程的老师编号列表
            List<SimpleUserInfo> simpleUserInfoList = new ArrayList<SimpleUserInfo>();
            AtomicReference<SimpleUserInfo> simpleUserInfoTemp = new AtomicReference<SimpleUserInfo>();
            if(!CollectionUtils.isEmpty(item.getTeachers())){
                item.getTeachers().stream().forEach(teacher -> {
                    simpleUserInfoTemp.set(SimpleUserInfo.builder().userCode(teacher.getId()).userName(teacher.getDisplay_name()).roleCode(RoleEnum.TEACHER.getRoleCode()).build());
                    simpleUserInfoList.add(simpleUserInfoTemp.get());
                });
            }

            //5.3 封装用户在该课程下的班级列表（含有用户在各个班级里的角色）
            ArrayList<SectionSimpleInfo> sectionList = new ArrayList<>();
            SectionSimpleInfo sectionTemp;
            if(!CollectionUtils.isEmpty(item.getSections())){
                for(SectionOfCanvas sectionOfCanvas : item.getSections()){
                    sectionTemp = SectionSimpleInfo.builder().sectionCode(sectionOfCanvas.getId()).sectionName(sectionOfCanvas.getName()).roleCode(sectionOfCanvas.getEnrollment_role()).build();
                    sectionList.add(sectionTemp);
                }
            }


            //5.4 填充该课程数据
            CourseInfo courseInfo = CourseInfo.builder().courseCode(item.getId())
                                                        .courseName(item.getName())
                                                        .courseColor(courseColor)
                                                        .termCode(item.getTerm().getId())
                                                        .termName(item.getTerm().getName())
                                                        .teacherInfoList(simpleUserInfoList)
                                                        .sectionList(sectionList).build();
            //5.5 如果班级列表不为空，则从中选出一个班级信息（对canvas系统不合理的用户数据做处理）
            if(!CollectionUtils.isEmpty(sectionList)){
                courseInfo.setSection(sectionList.get(0));
            }

            //5.6 课程列表中添加一门课程信息
            courseInfoListOfCurrentPage.add(courseInfo);
        });

        //6.封装分页数据，并返回
        PagedVo<List<CourseInfo>> coursePagedInfo = PagedVo.buildPagedVo(coursePagedInfoOfCanvas.getTotal(), courseInfoListOfCurrentPage);
        return coursePagedInfo;
    }

    /**
     * @author wangzhijun
     * @createdDate 2019.11.06
     * @Description 查询用户的课程列表（内部私有方法）
     * @param userCode
     * @return  List<CourseInfoOfCanvas>
     */
    private List<CourseInfoOfCanvas> queryCourseListByUserCode(Long userCode) throws BusinessException {


        //课程状态（unpublished：未发布的, available：有效的, completed：已完成的, deleted：已删除的）
        ArrayList<String> courseStateList = new ArrayList<String>();
        courseStateList.add("available");
        ArrayList<String> includeList = new ArrayList<String>();
        includeList.add("term");
        includeList.add("sections");
        includeList.add("teachers");
        //查询课程的第一页数据（默认每页显示10条数据）
        ResponseEntity<List<CourseInfoOfCanvas>> responseEntityTemp = canvasFeign.queryCoursePagedListOfUser(canvasFeignProperties.getSupperAdminToken(),
                                                                                                             userCode,
                                                                                                            "active",
                                                                                                             courseStateList,
                                                                                                             includeList,
                                                                                                            "sis_course_id",
                                                                                                            "asc",
                                                                                                            30,
                                                                                                            1);
        List<CourseInfoOfCanvas> courseInfoListOfCanvas = responseEntityTemp.getBody();

        //如果响应头中的Link属性有值，则获取Link
        HttpHeaders headers = responseEntityTemp.getHeaders();
        List<String> link = headers.get("Link");
        if(link != null && link.size()>0){
            //从Link中截取总页数
            Pattern pattern = Pattern.compile("(?<=page=)\\d+(?=&per_page=\\d+>; rel=\"last\")");
            Matcher matcher = pattern.matcher(link.get(0));
            //如果仅匹配到一个则为正常，否则抛出异常
            Long numberOfTotalPages = null;   //课程编号
            while(matcher.find()){
                numberOfTotalPages = switch(matcher.groupCount()){
                    case 0-> {
                        if(StringUtils.isBlank(matcher.group(0))){
                            yield null;
                        }
                        yield Long.parseLong(matcher.group(0));
                    }
                    default -> null;
                };
            }

            //如果总页数不为空且大于1，则用for循环取出所有课程数据
            if(numberOfTotalPages !=null && numberOfTotalPages.compareTo(1L)>0){
                List<CourseInfoOfCanvas> currentPagedListTemp;
                for(int i =2; i<=numberOfTotalPages; i++){
                    responseEntityTemp = canvasFeign.queryCoursePagedListOfUser(canvasFeignProperties.getSupperAdminToken(),
                                                                                userCode,
                                                                               "active",
                                                                                courseStateList,
                                                                                includeList,
                                                                               "sis_course_id",
                                                                               "asc",
                                                                               30,
                                                                                i);
                    currentPagedListTemp = responseEntityTemp.getBody();
                    if(currentPagedListTemp != null && currentPagedListTemp.size()>0){
                        courseInfoListOfCanvas.addAll(currentPagedListTemp);
                    }else{
                        throw BusinessException.getInstance(BusinessExceptionEnum.NOT_DATA_FOUND);
                    }
                }
            }
        }

        //返回该用户的所有课程列表
        return courseInfoListOfCanvas;
    }

    /**
     * @author wangzhijun
     * @createdDate 2019.11.06
     * @Description 根据课程编号查询班级列表（下拉列表）
     * @param courseCode
     * @return PagedVo<List<SectionInfo>>
     */
    @Override
    public List<SectionInfo> querySectionList(Long courseCode) throws BusinessException {

        //1.查询某课程的班级列表
        List<SectionInfoOfCanvas> sectionInfoListOfCanvas = querySectionListByCourseCode(courseCode);

        //2.封装班级下拉列表出参，并返回
        List<SectionInfo> sectionInfoList = new ArrayList<SectionInfo>();
        sectionInfoListOfCanvas.stream().forEach(item ->{
            SectionInfo sectionInfo = SectionInfo.builder().sectionCode(item.getId()).sectionName(item.getName()).totalNumOfStudents(item.getTotal_students()).build();
            ArrayList<Student> studentList = new ArrayList<>();
            Student studentTemp;
            for(StudentOfCanvas studentItem: item.getStudents()){
                studentTemp = Student.builder().userCode(studentItem.getId())
                                               .roleCode(RoleEnum.STUDENT.getRoleCode())
                                               .userName(studentItem.getName())
                                               .userNumber(studentItem.getSortable_name()).build();
                studentList.add(studentTemp);
            }
            sectionInfo.setStudentList(studentList);
            sectionInfoList.add(sectionInfo);
        });
        return sectionInfoList;
    }


    /**
     * @author wangzhijun
     * @createdDate 2019.11.06
     * @Description 查询课程的班级列表（内部私有方法）
     * @param courseCode
     * @return  List<SectionInfoOfCanvas>
     */
    private List<SectionInfoOfCanvas> querySectionListByCourseCode(Long courseCode) throws BusinessException {

        //添加请求参数includeList
        List<String> includeList = new ArrayList<String>();
        includeList.add("students");
        includeList.add("total_students");

        //查询课程的第一页数据（默认每页显示10条数据）
        ResponseEntity<List<SectionInfoOfCanvas>> responseEntityTemp = canvasFeign.querySectionPagedListOfCourse(canvasFeignProperties.getSupperAdminToken(),
                                                                                                                 courseCode,
                                                                                                                 includeList,
                                                                                                                 "id",
                                                                                                                 "asc",
                                                                                                                 30,
                                                                                                                 1);
        List<SectionInfoOfCanvas> sectionInfoListOfCanvas = responseEntityTemp.getBody();

        //如果响应头中的Link属性有值，则获取Link
        HttpHeaders headers = responseEntityTemp.getHeaders();
        List<String> link = headers.get("Link");
        if(link != null && link.size()>0){
            //从Link中截取总页数
            Pattern pattern = Pattern.compile("(?<=page=)\\d+(?=&per_page=\\d+>; rel=\"last\")");
            Matcher matcher = pattern.matcher(link.get(0));
            //如果仅匹配到一个则为正常，否则抛出异常
            Long numberOfTotalPages = null;   //课程编号
            while(matcher.find()){
                numberOfTotalPages = switch(matcher.groupCount()){
                    case 0-> {
                        if(StringUtils.isBlank(matcher.group(0))){
                            yield null;
                        }
                        yield Long.parseLong(matcher.group(0));
                    }
                    default -> null;
                };
            }

            //如果总页数不为空且大于1，则用for循环取出所有课程数据
            if(numberOfTotalPages !=null && numberOfTotalPages.compareTo(1L)>0){
                List<SectionInfoOfCanvas> currentPagedListTemp;
                for(int i =2; i<=numberOfTotalPages; i++){
                    responseEntityTemp = canvasFeign.querySectionPagedListOfCourse(canvasFeignProperties.getSupperAdminToken(),
                                                                                   courseCode,
                                                                                   includeList,
                                                                                   "id",
                                                                                   "asc",
                                                                                   30,
                                                                                    i);
                    currentPagedListTemp = responseEntityTemp.getBody();
                    if(currentPagedListTemp != null && currentPagedListTemp.size()>0){
                        sectionInfoListOfCanvas.addAll(currentPagedListTemp);
                    }else{
                        throw BusinessException.getInstance(BusinessExceptionEnum.NOT_DATA_FOUND);
                    }
                }
            }
        }

        //返回该课程的所有班级列表
        return sectionInfoListOfCanvas;
    }


}

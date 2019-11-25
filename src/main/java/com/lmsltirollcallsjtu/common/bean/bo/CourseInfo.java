package com.lmsltirollcallsjtu.common.bean.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;

@ApiModel
@Builder
@Data
public class CourseInfo {

    @ApiModelProperty(value = "课程编号", example = "1")
    private Long courseCode;    //课程编号
    @ApiModelProperty(value = "课程名称")
    private String courseName;   //课程名称
    @ApiModelProperty(value = "课程颜色")
    private String courseColor;   //课程颜色
    @ApiModelProperty(value = "学期编号", example = "1")
    private Long termCode;   //学期编号
    @ApiModelProperty(value = "学期名称")
    private String termName;   //学期名称
    @ApiModelProperty(value = "教师信息列表")
    private List<SimpleUserInfo> teacherInfoList;   //教师信息列表
    @ApiModelProperty(value = "班级列表")
    @JsonIgnore
    private List<SectionSimpleInfo> sectionList;   //班级列表
    @ApiModelProperty(value = "班级列表")
    private SectionSimpleInfo section;   //班级（从班级列表中选出一个）


    //添加函数或者构造方法，让lombok假装它不存在（不感知）
    @Tolerate
    public CourseInfo() {

    }
}

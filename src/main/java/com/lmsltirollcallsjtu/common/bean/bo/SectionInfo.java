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
public class SectionInfo {

    @ApiModelProperty(value = "班级编号", example = "1")
    private Long sectionCode;   //班级编号
    @ApiModelProperty(value = "班级名称")
    private String sectionName;    //班级名称
    @ApiModelProperty(value = "该班级的学生总数", example = "1")
    @JsonIgnore
    private Integer totalNumOfStudents;    //该班级的学生总数
    @ApiModelProperty(value = "该班级下的学生列表")
    @JsonIgnore
    private List<Student> studentList;  //该班级下的学生列表

    //添加函数或者构造方法，让lombok假装它不存在（不感知）
    @Tolerate
    public SectionInfo() {

    }

}

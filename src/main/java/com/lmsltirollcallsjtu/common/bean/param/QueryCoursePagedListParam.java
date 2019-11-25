package com.lmsltirollcallsjtu.common.bean.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel
@Data
public class QueryCoursePagedListParam {

    @ApiModelProperty(value = "当前页码", required = true)
    @NotNull(message = "pageNum不能为空！")
    private Integer pageNum;
    @ApiModelProperty(value = "每页显示数量", required = true)
    @NotNull(message = "pageSize不能为空！")
    private Integer pageSize;
    @ApiModelProperty(value = "模糊的课程名称", allowEmptyValue = true)
    private String vagueCourseName;   //模糊的课程名称


    //后台传入
    @ApiModelProperty(value = "用户编号", hidden = true)
    private Long userCode;
}

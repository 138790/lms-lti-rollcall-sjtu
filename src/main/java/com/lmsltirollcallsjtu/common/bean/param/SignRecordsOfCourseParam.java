package com.lmsltirollcallsjtu.common.bean.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotNull;

@ApiModel
@Data
public class SignRecordsOfCourseParam {

    @ApiModelProperty(value = "课程编号",required = true,example = "1")
    @NotNull(message = "课程编号不能为空！")
    private Long courseCode;
    @ApiModelProperty(value = "用户编号",required = true)
    @NotNull(message = "用户编号不能为空！")
    private Long userCode;

}

package com.lmsltirollcallsjtu.common.bean.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotNull;

@Data
@Builder
@ApiModel
public class CourseTopicParam {

    @ApiModelProperty(value = "课程编号", required = true)
    @NotNull(message = "课程编号不能为空！")
    private Long courseCode;//课程编号
    @ApiModelProperty(value = "主题编号", required = true)
    @NotNull(message = "主题编号不能为空！")
    private Long topicCode;//主题id
    @Tolerate
    public CourseTopicParam(){

    }
}

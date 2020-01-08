package com.lmsltirollcallsjtu.common.bean.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel
@Data
@Builder
public class SignRecordsInfo {

    @ApiModelProperty(value = "用户编号")
    private Long userCode;//用户编号
    @ApiModelProperty(value = "学生姓名")
    private String userName;//学生姓名
    @ApiModelProperty(value = "班级名称")
    private String sectionName;//班级名称
    @ApiModelProperty(value = "签到状态")
    private String state;//签到状态
//    private Integer isValid;//是否有效（1:有效,0:无效)
}

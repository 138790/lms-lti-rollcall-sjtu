package com.lmsltirollcallsjtu.common.bean.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@ApiModel
public class UserStates {

//    private String id;//表主键
    @ApiModelProperty(value="点名编号",required =true)
    @NotBlank(message = "点名编号不能为空")
    private String rollcallCode;//点名编号
    @ApiModelProperty(value="用户编号",required =true)
    private Long userCode;//用户编号
    @ApiModelProperty(value="修改者",required =true)
    private String updatedBy; //修改者
    @ApiModelProperty(value="用户状态",required =true)
    @NotBlank(message = "用户状态不能为空")
    private String state;//状态
//    private String userName;//学生姓名
    @ApiModelProperty(value="修改时间",required =true)
    private Date updatedDate;//修改时间
    @Tolerate
    public UserStates(){

    }
}

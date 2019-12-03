package com.lmsltirollcallsjtu.common.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Builder
@ApiModel
public class SignRecordDto {
    @ApiModelProperty(value = "学生表主键",required = true)
    @NotBlank(message = "主键id不能为空")
    private String id;//表记录id
    @ApiModelProperty(value = "用户编号",required = true)
    private Long userCode;//学生学号
    @ApiModelProperty(value = "点名编号",required = true)
    private String rollcallCode;//点名编号
//    private String openId;//绑定微信编号
    @ApiModelProperty(value = "学生签到状态",required = true)
    private String state;//签到状态
    @ApiModelProperty(value = "修改日期",required = true)
    private Date updatedDate;//修改时间
//    private Date createdDate;//创建时间
//    private String userName;//学生姓名
//    private String createdBy;//创建者
    @ApiModelProperty(value = "修改者",required = true)
    private String updatedBy;//修改者
//    private String name;//班级名称
    @ApiModelProperty(value = "签名密钥",required = true)
    private String signToken;//签名密钥
    @Tolerate
    public SignRecordDto(){

    }
}

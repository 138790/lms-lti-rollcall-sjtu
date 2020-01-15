package com.lmsltirollcallsjtu.common.bean.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import java.util.Date;

/*
  *@author huyong
  *@createdDate 2019-10-28
 */
@ApiModel
@Data
@Builder
public class SignRecordsDto {

    @ApiModelProperty(value = "表记录id")
    private String id;//表记录id
//    private Long userCode;//学生学号
//    private String rollcallCode;//点名编号
//    private String openId;//绑定微信编号
    @ApiModelProperty(value = "签到状态")
    private String state;//签到状态
//    private Date updatedDate;//修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createdDate;//创建时间
//    private String userName;//学生姓名
//    private String createdBy;//创建者
//    private String updatedBy;//创建者
//    private String name;//班级名称
//    private List<Students> students;

    @Tolerate
    public SignRecordsDto(){

    }
}

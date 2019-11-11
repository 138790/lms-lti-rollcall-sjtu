package com.lmsltirollcallsjtu.common.bean.dto;

import com.lmsltirollcallsjtu.common.bean.bo.Students;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/*
  *@author huyong
  *@createdDate 2019-10-28
 */
@Data
@Builder
public class SignRecordsDto {

    private String id;//表记录id
    private Long userCode;//学生学号
    private String rollcallCode;//点名编号
    private String openId;//绑定微信编号
    private String state;//签到状态
    private Date createdDate;//创建时间
//    private String createdBy;//创建者
//    private String updatedBy;//创建者
//    private String name;//班级名称
//    private List<Students> students;

    @Tolerate
    public SignRecordsDto(){

    }
}

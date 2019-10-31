package com.lmsltirollcallsjtu.common.bean.po;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.util.Date;
/*
  *@author huyong
  *@createdDate 2019-10-28
 */
@Data
@Builder
public class SignRecords {

    private Long id;//表记录id
    private Long userId;//学生编号
    private Long rollcallId;//点名编号
    private Long openId;//绑定微信编号
    private Date insertedAt;//插入时间
    private Date updatedAt;//修改时间
    @NotBlank(message = "签到状态不能为空")
    private String state;//签到状态
    @NotBlank(message = "学生姓名不能为空")
    private String userName;//学生姓名
}

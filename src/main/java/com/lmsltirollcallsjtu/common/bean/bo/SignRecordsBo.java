package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class SignRecordsBo {
    private String id; //表记录id
    private Long userCode; //学生学号
    private String rollcallCode; //点名编号
    private String openId; //绑定微信编号
    private String state; //签到状态
    private String sectionName; //班级名称
    private String userName; //学生姓名
    private String createdBy; //创建者
    private String updatedBy;  //修改者
    @Tolerate
    public SignRecordsBo(){
        
    }
}

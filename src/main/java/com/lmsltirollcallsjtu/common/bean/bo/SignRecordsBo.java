package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class SignRecordsBo {
    private String id;//表记录id
    private Integer userCode;//学生学号
    private String rollcallCode;//点名编号
    private String openId;//绑定微信编号
    private String state;//签到状态
//    private Date createdDate;//创建时间
    private String userName;//学生姓名
    @Tolerate
    public SignRecordsBo(){

    }
}

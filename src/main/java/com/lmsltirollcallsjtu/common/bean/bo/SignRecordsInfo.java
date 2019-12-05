package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignRecordsInfo {

    private Long userCode;//用户编号
    private String userName;//学生姓名
    private String sectionName;//班级名称
    private String state;//签到状态
//    private Integer isValid;//是否有效（1:有效,0:无效)
}

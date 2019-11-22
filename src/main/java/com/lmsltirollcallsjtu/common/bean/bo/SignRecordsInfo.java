package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignRecordsInfo {

    private String userName;//学生姓名
    private String sectionName;//班级名称
    private String state;//签到状态

}

package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Builder
@Data
public class Student {

    private Long userCode;    //用户编号 (对应canvas里的user_id)
    private String roleCode;   //角色编码
    private String userName;     //用户姓名
    private String userNumber;   //如果是学生的话，就是学号

    @Tolerate
    public Student(){

    }
}

package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class Students {
    private Integer id;    //学生编号
    private String name;//学生姓名

    @Tolerate
    public Students(){

    }
}

package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Builder
@Data
public class Section {

    private Integer sectionCode;//班级编号
    private String sectionName;//班级名字
    private Integer studenttotal;  //班级总人数

    @Tolerate
    public Section(){

    }
}

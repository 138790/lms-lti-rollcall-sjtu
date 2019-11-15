package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Builder
@Data
public class Section {

    private Long sectionCode;//班级编号
    private String sectionName;//班级名字
    private Long studentTotal;  //班级总人数

    @Tolerate
    public Section(){

    }
}

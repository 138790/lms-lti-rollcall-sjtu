package com.lmsltirollcallsjtu.common.bean.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@ApiModel
@Builder
@Data
public class SectionSimpleInfo {

    @ApiModelProperty(value = "班级编号", required = true)
    private Long sectionCode;
    @ApiModelProperty(value = "班级名称", required = true)
    private String sectionName;
    @ApiModelProperty(value = "角色编码", required = true)
    private String roleCode;


    @Tolerate
    public SectionSimpleInfo(){

    }
}

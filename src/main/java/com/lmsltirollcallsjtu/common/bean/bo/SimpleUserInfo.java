package com.lmsltirollcallsjtu.common.bean.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@ApiModel
@Builder
@Data
public class SimpleUserInfo {

    @ApiModelProperty(value = "用户编号")
    private Long userCode;
    @ApiModelProperty(value = "用户姓名")
    private String userName;
    @ApiModelProperty(value = "角色编码")
    private String roleCode;


    //添加函数或者构造方法，让lombok假装它不存在（不感知）
    @Tolerate
    public SimpleUserInfo() {

    }
}

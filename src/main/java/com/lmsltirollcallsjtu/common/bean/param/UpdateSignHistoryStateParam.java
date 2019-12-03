package com.lmsltirollcallsjtu.common.bean.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@ApiModel
@Data
public class UpdateSignHistoryStateParam {

    @ApiModelProperty(value = "签到扫描token签名", required = true)
    @NotBlank(message = "signScanToken不能为空！")
    private String signScanToken;


    //后台传入
    @ApiModelProperty(value = "用户编号", hidden = true)
    private Long userCode;
}

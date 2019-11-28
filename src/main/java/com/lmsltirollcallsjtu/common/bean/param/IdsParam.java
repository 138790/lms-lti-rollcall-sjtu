package com.lmsltirollcallsjtu.common.bean.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@ApiModel
public class IdsParam {

    @ApiModelProperty(value = "勾选的点名记录编号", required = true)
    @NotNull(message = "点名记录编号不能为空")
    private List<String> ids;//表主键id集合
    @ApiModelProperty(value = "用户编号", hidden = true)
    private Long userCode;//用户编号
    @Tolerate
    public IdsParam(){

    }
}

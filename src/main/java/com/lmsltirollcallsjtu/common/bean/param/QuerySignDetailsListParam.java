package com.lmsltirollcallsjtu.common.bean.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotNull;

@Data
@Builder
@ApiModel
public class QuerySignDetailsListParam {

    @ApiModelProperty(value = "课程编号",required = true,example = "1")
    @NotNull(message = "课程编号不能为空！")
    private String id;//点名编号
    @ApiModelProperty(value = "当前页码",required = true)
    @NotNull(message = "pageNum不能为空！")
    private Integer pageNum;//当前页码
    @ApiModelProperty(value = "每页显示数量",required = true)
    @NotNull(message = "pageSize不能为空！")
    private Integer pageSize;//每页显示数量
    @Tolerate
    public QuerySignDetailsListParam(){

    }
}

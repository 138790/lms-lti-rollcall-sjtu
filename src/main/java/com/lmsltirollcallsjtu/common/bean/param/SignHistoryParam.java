package com.lmsltirollcallsjtu.common.bean.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
@Data
public class SignHistoryParam {

    @ApiModelProperty(value="课程编号",required =true,example = "1")
    @NotNull(message = "课程编号不能为空！")
    private Long courseCode;
    @ApiModelProperty(value="班级编号",required = true)
    @NotEmpty(message = "班级编号不能为空！")
    private List<Long> sectionCodes;

    @ApiModelProperty(value="课程编号",hidden =true)
    private Long userCode;//用户编号
}

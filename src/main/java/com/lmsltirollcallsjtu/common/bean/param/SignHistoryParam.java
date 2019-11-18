package com.lmsltirollcallsjtu.common.bean.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.util.List;

@ApiModel
@Data
public class SignHistoryParam {

    @ApiModelProperty(value="课程编号",required =true)
    @NotBlank(message = "课程编号不能为空")
    private Long courseCode;
    @ApiModelProperty(value="班级编号",required = true)
    @NotBlank(message = "班级编号不能为空")
    private List<Long> sectionCodes;

}

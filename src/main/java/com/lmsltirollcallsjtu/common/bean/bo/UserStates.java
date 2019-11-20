package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class UserStates {

    private Long userCode;//用户编号
    private String updatedBy; //修改者
    private String state;//状态
    @Tolerate
    public UserStates(){

    }
}

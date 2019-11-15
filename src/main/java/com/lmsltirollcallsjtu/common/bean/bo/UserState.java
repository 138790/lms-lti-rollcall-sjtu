package com.lmsltirollcallsjtu.common.bean.bo;

import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class UserState {
    private String id;//表主键
    private String state;//学生签到状态
    private String userName;//学生姓名
    @Tolerate
    public UserState(){

    }
}

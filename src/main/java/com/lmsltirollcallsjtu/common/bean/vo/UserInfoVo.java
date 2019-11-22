package com.lmsltirollcallsjtu.common.bean.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class UserInfoVo {

    private String id;//UUID生成的表主键，点名编号

    @Tolerate
    public UserInfoVo(){

    }
}

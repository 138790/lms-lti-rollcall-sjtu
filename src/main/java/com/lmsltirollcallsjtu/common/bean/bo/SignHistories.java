package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class SignHistories {

    private String id;//点名编号
    private Integer attendancesCount;//签到总数
    @Tolerate
    public SignHistories(){

    }
}

package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
public class SignId {

    private String signHistoryId;//签到表编号
    private String sign;//签名
    @Tolerate
    public SignId(){

    }
}

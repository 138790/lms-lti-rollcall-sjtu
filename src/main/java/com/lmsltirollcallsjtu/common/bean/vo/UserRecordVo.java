package com.lmsltirollcallsjtu.common.bean.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import java.util.Date;

@Data
@Builder
public class UserRecordVo {

    private String id;//点名表主键
    private String sign;//签名
    @Tolerate
    public UserRecordVo(){

    }
}

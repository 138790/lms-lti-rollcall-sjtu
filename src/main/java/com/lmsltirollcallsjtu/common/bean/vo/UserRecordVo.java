package com.lmsltirollcallsjtu.common.bean.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import java.util.Date;

@Data
@Builder
public class UserRecordVo {

    private String id;//UUID生成的表主键，点名编号
    private Date date;//创建对象时间
    @Tolerate
    public UserRecordVo(){

    }
}

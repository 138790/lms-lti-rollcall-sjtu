package com.lmsltirollcallsjtu.common.bean.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import java.util.Date;

@Data
@Builder
public class UserStateInfo {

    private String state;//用户签到状态
    private String userName;//用户名
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedDate;//签到时间
    @Tolerate
    public UserStateInfo(){

    }
}

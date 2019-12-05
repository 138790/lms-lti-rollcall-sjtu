package com.lmsltirollcallsjtu.common.enums;

import lombok.Getter;

public enum SignInStateEnum {

    UNNORMAL("UNNORMAL","未签到"),
    NORMAL("NORMAL","正常"),
    LATE("LATE","迟到"),
    LEAVE_EARLY("LEAVE_EARLY","早退"),
    TRUANT("TRUANT","旷课"),
    ETC("ETC","其他"),
    AMEND("AMEND","补签"),
    PERSONAL_LEAVE("PERSONAL_LEAVE","事假"),
    SICK_LEAVE("SICK_LEAVE","病假");


    @Getter
    private String code;
    @Getter
    private String description;

    SignInStateEnum(String code, String description){

        this.code = code;
        this.description = description;
    }

}

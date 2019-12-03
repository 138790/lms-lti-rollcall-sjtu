package com.lmsltirollcallsjtu.common.bean.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Builder
@Data
public class SignScanQuartzJobDto {

    private String signScanQuartzJobLogId;
    private String signRecordId;
    private String jobName;
    private String jobGroup;
    private String triggerName;
    private String triggerGroup;
    private String isValid;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;

    @Tolerate
    public SignScanQuartzJobDto(){

    }
}

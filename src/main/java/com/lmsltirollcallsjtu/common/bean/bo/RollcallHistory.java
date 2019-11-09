package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;
@Data
@Builder
public class RollcallHistory {
    private Long id;
    private Long courseCode;
    private Long userCode;
    private Long attendancesCount;
    private List<Long> sectionCodes;

    @Tolerate
    public RollcallHistory(){

    }
}

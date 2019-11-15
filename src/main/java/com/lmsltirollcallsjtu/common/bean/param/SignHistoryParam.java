package com.lmsltirollcallsjtu.common.bean.param;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SignHistoryParam {
    private Long courseCode;
    private List<Long> sectionCodes;
}

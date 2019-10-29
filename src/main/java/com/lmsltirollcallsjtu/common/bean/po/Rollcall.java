package com.lmsltirollcallsjtu.common.bean.po;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/*
 *@author huyong
 * @createdDate 2019-10-28
 */
@Data
@Builder
public class Rollcall {
    private Long id;
    private Long courseId;
    private String userId;
    private Date insertedAt;
    private Date updatedAt;
    private List<Integer> sectionIds;
    private Long attendancesCount;
}

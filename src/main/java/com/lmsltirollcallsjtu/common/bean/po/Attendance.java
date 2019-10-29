package com.lmsltirollcallsjtu.common.bean.po;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
/*
  *@author huyong
  *@createdDate 2019-10-28
 */
@Data
@Builder
public class Attendance {
    private Long id;
    private Long userId;
    private Long rollcallId;
    private Date insertedAt;
    private Date updatedAt;
    private String state;
    private String opedId;
}

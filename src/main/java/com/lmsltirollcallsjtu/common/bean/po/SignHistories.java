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
public class SignHistories {

    private Long id;//表记录编号
    private Long courseId;//课程编号
    private Long userId;//学生编号
    private Date insertedAt;//插入时间
    private Date updatedAt;//修改时间
    private List<Integer> sectionIds;//班级编号
    private Long attendancesCount;//签到总数
}

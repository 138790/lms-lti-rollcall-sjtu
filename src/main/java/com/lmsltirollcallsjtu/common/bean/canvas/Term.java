package com.lmsltirollcallsjtu.common.bean.canvas;

import lombok.Data;

import java.util.Date;

@Data
public class Term {

    private Long id;   //学期ID
    private String name;  //学期名称
    private Date start_at;  //开始时间
    private Date end_at;  //结束时间
    private Date created_at;  //创建时间
    private String workflow_state;  //状态（示例值："active"）
    private String grading_period_group_id;

}

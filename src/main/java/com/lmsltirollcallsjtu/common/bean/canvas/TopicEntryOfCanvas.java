package com.lmsltirollcallsjtu.common.bean.canvas;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class TopicEntryOfCanvas {

    private Long id ;//条目主键id
    private Long user_id;//用户id
    private Long parent_id;//父及编号（上一级编号）
    private Date created_at;//回复创建时间
    private Date updated_at;//回复修改时间
    private String user_name;//发起讨论用户名
    private String message;//回复内容
    private String read_state;//读状态
    @Tolerate
    public TopicEntryOfCanvas(){

    }
}

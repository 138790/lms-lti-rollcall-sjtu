package com.lmsltirollcallsjtu.common.bean.canvas;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class TopicDetailsOfCanvas {

    private Long id;//主题id
    private String title;//标题内容
    private Boolean is_section_specific;//是否有具体的班级
    private String user_name;//发起讨论者
    private Integer discussion_subentry_count;//讨论总人数
    private String message;//讨论的问题内容

    @Tolerate
    public TopicDetailsOfCanvas(){

    }
}

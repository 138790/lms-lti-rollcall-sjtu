package com.lmsltirollcallsjtu.common.bean.canvas;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import java.util.Date;

@Data
@Builder
public class CourseDiscussionTopicOfCanvas {

    private Long id;//主题id
    private String title;//主题标题
    private Date last_reply_at;//讨论截止时间
    private Date created_at;//讨论发起时间
    private String discussion_type;//讨论类型
    private Date lock_at;//讨论可供使用的开始时间
    private Boolean is_section_specific;//是否需要确定班级
    private Integer discussion_subentry_count;//讨论人数
    private Permission permissions;
    private String require_initial_post;
    private Boolean user_can_see_posts;
    private String read_state;//阅读回复状态
    private Integer unread_count;//未读回复的数量
    private Boolean subscribed;//是否订阅
    private Boolean published;//是否发布
    private Boolean can_unpublish;//是否可以不发布
    private Boolean locked;//是否上锁
    private Boolean can_lock;//是否可锁
    private Boolean comments_disabled;//是否可评论

    @Tolerate
    public CourseDiscussionTopicOfCanvas(){

    }
}

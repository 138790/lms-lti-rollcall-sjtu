package com.lmsltirollcallsjtu.common.bean.canvas;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class AuthorOfCanvas {

    private Long id;//用户id;
    private String display_name;//发布讨论的名字
    private String avatar_image_url;
    private String html_url;
    @Tolerate
    public AuthorOfCanvas(){

    }
}

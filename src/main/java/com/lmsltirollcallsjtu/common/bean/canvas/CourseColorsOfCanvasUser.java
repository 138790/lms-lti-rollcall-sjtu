package com.lmsltirollcallsjtu.common.bean.canvas;

import lombok.Data;

import java.util.Map;

@Data
public class CourseColorsOfCanvasUser {

    private Map<String,String> custom_colors;   //键值对示例："course_42":"#abc123"
}

package com.lmsltirollcallsjtu.common.service;


import com.lmsltirollcallsjtu.common.bean.canvas.CourseDiscussionTopicOfCanvas;
import com.lmsltirollcallsjtu.common.bean.canvas.DiscussionDetailsEntriesOfCanvas;
import com.lmsltirollcallsjtu.common.bean.param.CourseTopicParam;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import java.util.List;

public interface CanvasDiscussionService {

    //根据课程编号查询课程的讨论主题列表
    public List<CourseDiscussionTopicOfCanvas> queryDiscussionTopicListOfCanvas(Long courseCode) throws BusinessException;

    //根据课程编号和主题编号查询某一讨论的详情和回复条目列表
    public DiscussionDetailsEntriesOfCanvas queryDiscussionDetailsEntriesOfCanvas(CourseTopicParam courseTopicParam) throws BusinessException;
}

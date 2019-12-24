package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.bean.canvas.*;
import com.lmsltirollcallsjtu.common.bean.param.CourseTopicParam;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.feign.CanvasFeign;
import com.lmsltirollcallsjtu.common.properties.CanvasFeignProperties;
import com.lmsltirollcallsjtu.common.service.CanvasDiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CanvasDiscussionServiceImpl implements CanvasDiscussionService {
    @Autowired
    private CanvasFeign canvasFeign;
    @Autowired
    private CanvasFeignProperties canvasFeignProperties;
    @Override
    public List<CourseDiscussionTopicOfCanvas> queryDiscussionTopicListOfCanvas(Long courseCode) throws BusinessException {

        ResponseEntity<List<CourseDiscussionTopicOfCanvas>> responseEntityTemp = canvasFeign.queryDiscussionTopicListOfCanvas(canvasFeignProperties.getSupperAdminToken(),
                                                                                                                              courseCode,
                                                                                                                             "id",
                                                                                                                             "desc",
                                                                                                                             30,
                                                                                                                             1);
        List<CourseDiscussionTopicOfCanvas> discussionTopicListOfCanvas = responseEntityTemp.getBody();
        //如果响应头中的Link属性有值，则获取Link
        HttpHeaders headers = responseEntityTemp.getHeaders();
        List<String> link = headers.get("Link");
        if (link != null && link.size() > 0) {
            //从Link中截取总页数
            Pattern pattern = Pattern.compile("(?<=page=)\\d+(?=&per_page=\\d+>; rel=\"last\")");
            Matcher matcher = pattern.matcher(link.get(0));
            //如果仅匹配到一个则为正常，否则抛出异常
            Long numberOfTotalPages = null;   //总页数
            while (matcher.find()) {
                numberOfTotalPages = switch (matcher.groupCount()) {
                    case 0 -> {
                        if (StringUtils.isEmpty(matcher.group(0))) {
                            yield null;
                        }
                        yield Long.parseLong(matcher.group(0));
                    }
                    default -> null;
                };
            }
            //如果总页数不为空且大于1，则用for循环取出所有讨论列表数据
            if (numberOfTotalPages != null && numberOfTotalPages.compareTo(1L) > 0) {
                List<CourseDiscussionTopicOfCanvas> currentPagedListTemp;
                for (int i = 2; i <= numberOfTotalPages; i++) {
                    responseEntityTemp = canvasFeign.queryDiscussionTopicListOfCanvas(canvasFeignProperties.getSupperAdminToken(),
                                                                                      courseCode,
                                                                                     "id",
                                                                                     "desc",
                                                                                     30,
                                                                                      i);
                    currentPagedListTemp = responseEntityTemp.getBody();
                    if (currentPagedListTemp != null && currentPagedListTemp.size() > 0) {
                        discussionTopicListOfCanvas.addAll(currentPagedListTemp);
                    } else {
                        throw BusinessException.getInstance(BusinessExceptionEnum.NOT_DATA_FOUND);
                    }
                }
            }

        }
        return discussionTopicListOfCanvas;
    }
    @Override
    public DiscussionDetailsEntriesOfCanvas queryDiscussionDetailsEntriesOfCanvas(CourseTopicParam courseTopicParam) throws BusinessException {
        TopicDetailsOfCanvas topicDetailsOfCanvas = canvasFeign.querySingleTopicOfCanvas(canvasFeignProperties.getSupperAdminToken(),
                                                                                         courseTopicParam.getCourseCode(),
                                                                                         courseTopicParam.getTopicCode());
        ResponseEntity<List<CourseTopicEntryOfCanvas>> responseEntityTemp = canvasFeign.queryCourseTopicEntryList(canvasFeignProperties.getSupperAdminToken(),
                                                                                                                  courseTopicParam.getCourseCode(),
                                                                                                                  courseTopicParam.getTopicCode(),
                                                                                                                 "created_at",
                                                                                                                 "asc",
                                                                                                                 30,
                                                                                                                 1);
        List<CourseTopicEntryOfCanvas> courseTopicEntryOfCanvas= responseEntityTemp.getBody();
        //如果响应头中的Link属性有值，则获取Link
        HttpHeaders headers = responseEntityTemp.getHeaders();
        List<String> link = headers.get("Link");
        DiscussionDetailsEntriesOfCanvas discussionDetailsEntriesOfCanvas = null;
        if (link != null && link.size() > 0) {
            //从Link中截取总页数
            Pattern pattern = Pattern.compile("(?<=page=)\\d+(?=&per_page=\\d+>; rel=\"last\")");
            Matcher matcher = pattern.matcher(link.get(0));
            //如果仅匹配到一个则为正常，否则抛出异常
            Long numberOfTotalPages = null;   //总页数
            while (matcher.find()) {
                numberOfTotalPages = switch (matcher.groupCount()) {
                    case 0 -> {
                        if (StringUtils.isEmpty(matcher.group(0))) {
                            yield null;
                        }
                        yield Long.parseLong(matcher.group(0));
                    }
                    default -> null;
                };
            }
            //如果总页数不为空且大于1，则用for循环取出所有讨论列表数据
            if (numberOfTotalPages != null && numberOfTotalPages.compareTo(1L) > 0) {
                List<CourseTopicEntryOfCanvas> currentPagedListTemp;
                for (int i = 2; i <= numberOfTotalPages; i++) {
                    responseEntityTemp = canvasFeign.queryCourseTopicEntryList(canvasFeignProperties.getSupperAdminToken(),
                                                                               courseTopicParam.getCourseCode(),
                                                                               courseTopicParam.getTopicCode(),
                                                                              "created_at",
                                                                              "asc",
                                                                              30,
                                                                               i);
                    currentPagedListTemp = responseEntityTemp.getBody();
                    if (currentPagedListTemp != null && currentPagedListTemp.size() > 0) {
                        courseTopicEntryOfCanvas.addAll(currentPagedListTemp);
                    } else {
                        throw BusinessException.getInstance(BusinessExceptionEnum.NOT_DATA_FOUND);
                    }
                }
            }

        }
        discussionDetailsEntriesOfCanvas = DiscussionDetailsEntriesOfCanvas.builder().topicDetails(topicDetailsOfCanvas)
                                                                                     .courseTopicEntryList(courseTopicEntryOfCanvas).build();
        return discussionDetailsEntriesOfCanvas;
    }
    }



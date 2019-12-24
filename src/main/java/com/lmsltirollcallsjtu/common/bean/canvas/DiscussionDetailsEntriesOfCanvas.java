package com.lmsltirollcallsjtu.common.bean.canvas;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;

@Data
@Builder
public class DiscussionDetailsEntriesOfCanvas {

   private TopicDetailsOfCanvas topicDetails;
   private List<CourseTopicEntryOfCanvas> courseTopicEntryList;

   @Tolerate
    public DiscussionDetailsEntriesOfCanvas(){

   }
}

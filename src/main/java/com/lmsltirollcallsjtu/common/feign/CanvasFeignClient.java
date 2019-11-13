package com.lmsltirollcallsjtu.common.feign;

import com.lmsltirollcallsjtu.common.bean.bo.Enrollments;
import com.lmsltirollcallsjtu.common.bean.bo.SectionsOfCanvas;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @FeignClient标签的常用属性如下：
 * name：指定FeignClient的名称，如果项目使用了Ribbon，name属性会作为微服务的名称，用于服务发现
 * url: url一般用于调试，可以手动指定@FeignClient调用的地址
 * decode404:当发生http 404错误时，如果该字段位true，会调用decoder进行解码，否则抛出FeignException
 * configuration: Feign配置类，可以自定义Feign的Encoder、Decoder、LogLevel、Contract
 * fallback: 定义容错的处理类，当调用远程接口失败或超时时，会调用对应接口的容错逻辑，fallback指定的类必须实现@FeignClient标记的接口
 * fallbackFactory: 工厂类，用于生成fallback类示例，通过这个属性我们可以实现每个接口通用的容错逻辑，减少重复的代码
 * path: 定义当前FeignClient的统一前缀
 */
@FeignClient(name = "${feign.client.application-name}", url = "${feign.client.url}")
public interface CanvasFeignClient {

    /**
     * @descritpion 调用远程接口 ${feign.client.url}/api/v1/courses/{courseId}/sections
     * @param
     * @return
     */
    //列出某一课程下的班级
    //GET /api/v1/courses/:course_id/sections
    @GetMapping(value = "/api/v1/courses/{courseId}/sections")
   ResponseEntity<List<SectionsOfCanvas>> getSections(@RequestHeader(name="Authorization",required = true) String bearerToken,
                                                      @PathVariable("courseId") Integer courseId,
                                                      @RequestParam("include") String includeTotalStudents);


    //获取某一课程下某一个班级的信息
    @GetMapping(value="/api/v1/courses/{courseId}/sections/{id}")
    ResponseEntity<SectionsOfCanvas> getSectionDetail(@RequestHeader("Authorization") String bearerToken,
                                              @PathVariable("courseId") Integer courseId,
                                              @PathVariable("id") Integer id,
                                              @RequestParam("include") List<String> includeList);

    //GET/api/v1/sections/:section_id/enrollments
    @GetMapping(value = "/api/v1/sections/{sectionId}/enrollments")
    ResponseEntity<List<Enrollments>> getEnrollmentsCount(@RequestHeader (name="Authorization",required = true) String bearerToken,
                                                          @PathVariable("sectionId") Integer sectionId);
}

package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.bean.bo.Sections;
import com.lmsltirollcallsjtu.common.config.CanvasFeignProperties;
import com.lmsltirollcallsjtu.common.feign.CanvasFeignClient;
import com.lmsltirollcallsjtu.common.service.CanvasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class CanvasServiceImpl  {

    /*@Autowired
    private CanvasFeignClient canvasFeignClient;
    @Autowired
    private CanvasFeignProperties canvasFeignProperties;


    @Override
    public List<Sections> getSections(String bearerToken,Long courseId) {
        ResponseEntity<List<Sections>> responseEntityTemp= canvasFeignClient.getSections(canvasFeignProperties.getSupperAdminToken(),courseId);
        List<Sections> sections = responseEntityTemp.getBody();
        HttpHeaders headers = responseEntityTemp.getHeaders();
        List<String> link = headers.get("Link");
        sections.get(0).getName()
        return sections;
    }


    private List<Sections> getSectionDetails(String bearerToken,Long courseId,Long id,Long includeTotalStudents,String includeStudentsName){
        ResponseEntity<Sections> responseEntityTemp = canvasFeignClient.getSectionDetail(canvasFeignProperties.getSupperAdminToken(), courseId, id, includeTotalStudents, includeStudentsName);
        Sections sections= responseEntityTemp.getBody();
        HttpHeaders headers = responseEntityTemp.getHeaders();
        List<String> link = headers.get("Link");
        List<Sections> sectionsList = new ArrayList<>();
        sectionsList.add(sections);
        return sectionsList;
    }
    @Override
    public List<Sections> getSectionDetail(String bearerToken,Long courseId,Long id,Long includeTotalStudents,String includeStudentsName) {*/
       // ResponseEntity<Sections> responseEntityTemp = canvasFeignClient.getSectionDetail(canvasFeignProperties.getSupperAdminToken(), courseId, id,includeTotalStudents,includeStudentsName);
     //*  List<Sections> responseEntity= canvasFeignClient.getSectionDetail(canvasFeignProperties.getSupperAdminToken(),courseId,id,includeTotalStudents,includeStudentsName);
//        List<Sections> sections = responseEntity.getBody();
//        HttpHeaders headers = responseEntity.getHeaders();
//        List<String> link = headers.get("Link");*//*


}

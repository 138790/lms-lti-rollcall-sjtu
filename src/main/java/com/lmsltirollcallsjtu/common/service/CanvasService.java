package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.bo.Sections;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CanvasService {

    List<Sections>getSections(String bearerToken,Long courseId);

    List<Sections> getSectionDetail(String bearerToken,Long courseId,Long sectionId);
}

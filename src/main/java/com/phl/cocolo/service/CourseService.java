package com.phl.cocolo.service;

import com.phl.cocolo.dto.CourseDetailDTO;
import com.phl.cocolo.dto.CourseSaveDTO;
import com.phl.cocolo.dto.CourseUpdateDTO;
import com.phl.cocolo.dto.MentoringDetailDTO;

import java.io.IOException;
import java.util.List;

public interface CourseService {
    Long save(CourseSaveDTO courseSaveDTO) throws IllegalStateException, IOException;

    void deleteById(Long courseId);

    List<CourseDetailDTO> findAll(Long onClassId);

    CourseDetailDTO findById(Long courseId);

    void update(CourseUpdateDTO courseUpdateDTO);
}

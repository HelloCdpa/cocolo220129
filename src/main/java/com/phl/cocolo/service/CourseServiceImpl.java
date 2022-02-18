package com.phl.cocolo.service;

import com.phl.cocolo.dto.*;
import com.phl.cocolo.entity.CourseEntity;
import com.phl.cocolo.entity.OnClassEntity;
import com.phl.cocolo.repository.CourseRepository;
import com.phl.cocolo.repository.OnClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{
    private final CourseRepository cr;
    private final OnClassRepository or;


    @Override
    public Long save(CourseSaveDTO courseSaveDTO) {

        OnClassEntity onClassEntity = or.findById(courseSaveDTO.getOnClassId()).get();
        CourseEntity courseEntity = CourseEntity.toCategorySaveEntity(courseSaveDTO,onClassEntity);

        return cr.save(courseEntity).getId();
    }

    @Override
    public void deleteById(Long courseId) {
        cr.findById(courseId);
    }

    @Override
    public List<CourseDetailDTO> findAll(Long onClassId) {
        OnClassEntity onClassEntity = or.findById(onClassId).get();
        List<CourseEntity> courseEntityList = onClassEntity.getCourseEntityList();
        List<CourseDetailDTO> courseList = new ArrayList<>();
        for(CourseEntity c: courseEntityList){
            courseList.add(CourseDetailDTO.toCourseDetailDTO(c));
        }

        return courseList;

    }

    @Override
    public CourseDetailDTO findById(Long courseId) {
        Optional<CourseEntity> optionalCourseEntity = cr.findById(courseId);
        CourseDetailDTO courseDetailDTO = CourseDetailDTO.toCourseDetailDTO(optionalCourseEntity.get());
        return courseDetailDTO;
    }

    @Override
    public void update(CourseUpdateDTO courseUpdateDTO) {
        OnClassEntity onClassEntity = or.findById(courseUpdateDTO.getOnClassId()).get();
        CourseEntity courseEntity = CourseEntity.toCategoryUpdateEntity(courseUpdateDTO,onClassEntity);

         cr.save(courseEntity);
    }
}

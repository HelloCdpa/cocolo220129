package com.phl.cocolo.dto;

import com.phl.cocolo.entity.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailDTO {
    private Long courseId;
    private Long onClassId;

    private String courseTitle;
    private String courseSection;
    private String courseContents;
    private String courseFileName;

    // 선생님, 카테고리
    private String onClassTeacher;
    private String onClassCate;


    public static CourseDetailDTO toCourseDetailDTO(CourseEntity courseEntity){
        CourseDetailDTO courseDetailDTO = new CourseDetailDTO();

        courseDetailDTO.setCourseId(courseEntity.getId());
        courseDetailDTO.setOnClassId(courseEntity.getOnClassEntity().getId());

        courseDetailDTO.setCourseTitle(courseEntity.getCourseTitle());
        courseDetailDTO.setCourseSection(courseEntity.getCourseSection());
        courseDetailDTO.setCourseContents(courseEntity.getCourseContents());
        courseDetailDTO.setCourseFileName(courseEntity.getCourseFileName());

        courseDetailDTO.setOnClassCate(courseEntity.getOnClassEntity().getOnClassCate());
        courseDetailDTO.setOnClassTeacher(courseEntity.getOnClassEntity().getOnClassTeacher());

        return courseDetailDTO;
    }
}

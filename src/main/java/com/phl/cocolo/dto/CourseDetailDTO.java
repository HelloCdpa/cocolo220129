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

    private String courseSection;
    private String courseContents;
    private String courseFileName;

    public static CourseDetailDTO toCourseDetailDTO(CourseEntity courseEntity){
        CourseDetailDTO courseDetailDTO = new CourseDetailDTO();

        courseDetailDTO.setCourseId(courseEntity.getId());
        courseDetailDTO.setOnClassId(courseEntity.getOnClassEntity().getId());

        courseDetailDTO.setCourseSection(courseEntity.getCourseSection());
        courseDetailDTO.setCourseContents(courseEntity.getCourseContents());
        courseDetailDTO.setCourseFileName(courseEntity.getCourseFileName());

        return courseDetailDTO;
    }
}

package com.phl.cocolo.entity;

import com.phl.cocolo.dto.CourseSaveDTO;
import com.phl.cocolo.dto.CourseUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "onClass_course_table")
public class CourseEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "onClass_id")
    private OnClassEntity onClassEntity;

    @Column
    private String courseSection;

    @Column
    private String courseTitle;

    @Column
    private String courseFileName;

    @Column
    private String courseContents;

    public static CourseEntity toCategorySaveEntity(CourseSaveDTO courseSaveDTO, OnClassEntity onClassEntity){
        CourseEntity courseEntity = new CourseEntity();

        courseEntity.setOnClassEntity(onClassEntity);
        courseEntity.setCourseTitle(courseSaveDTO.getCourseTitle());
        courseEntity.setCourseContents(courseSaveDTO.getCourseContents());
        courseEntity.setCourseFileName(courseSaveDTO.getCourseFileName());
        courseEntity.setCourseSection(courseSaveDTO.getCourseSection());

        return courseEntity;
    }
    public static CourseEntity toCategoryUpdateEntity(CourseUpdateDTO courseUpdateDTO, OnClassEntity onClassEntity){
        CourseEntity courseEntity = new CourseEntity();

        courseEntity.setId(courseUpdateDTO.getCourseId());
        courseEntity.setOnClassEntity(onClassEntity);

        courseEntity.setCourseTitle(courseUpdateDTO.getCourseTitle());
        courseEntity.setCourseContents(courseUpdateDTO.getCourseContents());
        courseEntity.setCourseFileName(courseUpdateDTO.getCourseFileName());
        courseEntity.setCourseSection(courseUpdateDTO.getCourseSection());

        return courseEntity;
    }





}

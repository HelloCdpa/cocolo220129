package com.phl.cocolo.entity;

import com.phl.cocolo.dto.OnClassCourseSaveDTO;
import com.phl.cocolo.dto.OnClassCourseUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "onClass_course_table")
public class OnClassCourseEntity extends BaseEntity {
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

    public static OnClassCourseEntity toCategorySaveEntity(OnClassCourseSaveDTO onClassCourseSaveDTO,OnClassEntity onClassEntity){
        OnClassCourseEntity onClassCourseEntity = new OnClassCourseEntity();

        onClassCourseEntity.setOnClassEntity(onClassEntity);
        onClassCourseEntity.setCourseTitle(onClassCourseSaveDTO.getCourseTitle());
        onClassCourseEntity.setCourseContents(onClassCourseSaveDTO.getCourseContents());
        onClassCourseEntity.setCourseFileName(onClassCourseSaveDTO.getCourseFileName());
        onClassCourseEntity.setCourseSection(onClassCourseSaveDTO.getCourseSection());

        return onClassCourseEntity;
    }
    public static OnClassCourseEntity toCategoryUpdateEntity(OnClassCourseUpdateDTO onClassCourseUpdateDTO, OnClassEntity onClassEntity){
        OnClassCourseEntity onClassCourseEntity = new OnClassCourseEntity();

        onClassCourseEntity.setId(onClassCourseUpdateDTO.getCourseId());
        onClassCourseEntity.setOnClassEntity(onClassEntity);

        onClassCourseEntity.setCourseTitle(onClassCourseUpdateDTO.getCourseTitle());
        onClassCourseEntity.setCourseContents(onClassCourseUpdateDTO.getCourseContents());
        onClassCourseEntity.setCourseFileName(onClassCourseUpdateDTO.getCourseFileName());
        onClassCourseEntity.setCourseSection(onClassCourseUpdateDTO.getCourseSection());

        return onClassCourseEntity;
    }





}

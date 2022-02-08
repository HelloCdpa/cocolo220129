package com.phl.cocolo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "onClass_course_table")
public class OnClassCourseEntity {
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
}

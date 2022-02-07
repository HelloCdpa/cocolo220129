package com.phl.cocolo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "study_table")
public class StudyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @Column
    private String studyTitle;

    @Column
    private String studyContents;

    @Column
    private String studyPlace;

    @Column
    private String studyMax;

    @Column(columnDefinition = "integer default 0")
    private String studyCount;

    @Column
    private String studyCate;



}

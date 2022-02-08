package com.phl.cocolo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "onClass_table")
public class OnClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "onClass_id")
    private Long id;

    @Column
    private String onClassTeacher;

    @Column
    private String onClassTitle;

    @Column
    private String onClassContents;

    @Column
    private String onClassCate;

    @Column
    private String onClassIntro;

    @OneToMany(mappedBy = "onClassEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviewEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "onClassEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OnClassCourseEntity> onClassCourseEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "onClassEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MyClassEntity> myClassEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "onClassEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartEntity> cartEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "onClassEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishListEntity> wishListEntityList = new ArrayList<>();





}

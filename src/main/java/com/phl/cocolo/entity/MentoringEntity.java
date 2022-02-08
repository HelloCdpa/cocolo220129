package com.phl.cocolo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "mentoring_table")
public class MentoringEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentoring_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @Column
    private String mentoringCate;

    @Column
    private String mentoringTitle;

    @Column
    private String mentoringContents;

    @Column(columnDefinition = "integer default 0")
    private int mentoringCount;

    @Column(columnDefinition = "integer default 0")
    private int mentoringPrice;

    @Column
    private String mentoringCareer;

    @OneToMany(mappedBy = "mentoringEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenteeEntity> menteeEntityList = new ArrayList<>();
}

package com.phl.cocolo.entity;

import com.phl.cocolo.dto.StudySaveDTO;
import com.phl.cocolo.dto.StudyUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "study_table")
public class StudyEntity extends BaseEntity{
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
    private int studyMax;

    @Column(columnDefinition = "integer default 0")
    private String studyCount;

    @Column
    private String studyCate;

    public static StudyEntity toStudySaveEntity(StudySaveDTO studySaveDTO,MemberEntity memberEntity){
        StudyEntity studyEntity = new StudyEntity();

        studyEntity.setMemberEntity(memberEntity);
        studyEntity.setStudyTitle(studySaveDTO.getStudyTitle());
        studyEntity.setStudyContents(studySaveDTO.getStudyContents());
        studyEntity.setStudyPlace(studySaveDTO.getStudyPlace());
        studyEntity.setStudyMax(studySaveDTO.getStudyMax());
        studyEntity.setStudyCate(studySaveDTO.getStudyCate());

        return studyEntity;
    }
    public static StudyEntity toStudyUpdateEntity(StudyUpdateDTO studyUpdateDTO, MemberEntity memberEntity){
        StudyEntity studyEntity = new StudyEntity();

        studyEntity.setId(studyUpdateDTO.getStudyId());

        studyEntity.setMemberEntity(memberEntity);
        studyEntity.setStudyTitle(studyUpdateDTO.getStudyTitle());
        studyEntity.setStudyContents(studyUpdateDTO.getStudyContents());
        studyEntity.setStudyPlace(studyUpdateDTO.getStudyPlace());
        studyEntity.setStudyMax(studyUpdateDTO.getStudyMax());
        studyEntity.setStudyCate(studyUpdateDTO.getStudyCate());

        return studyEntity;
    }

}

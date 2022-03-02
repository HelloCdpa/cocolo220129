package com.phl.cocolo.entity;

import com.phl.cocolo.dto.StudyUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "study_apply_table")
public class StudyApplyEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private StudyEntity studyEntity;

    @Column(columnDefinition = "boolean default false")
    private boolean applyState;

    public static StudyApplyEntity toStudyApplySaveEntity(MemberEntity memberEntity,
                                                          StudyEntity studyEntity){
        StudyApplyEntity studyApplyEntity = new StudyApplyEntity();

        studyApplyEntity.setMemberEntity(memberEntity);
        studyApplyEntity.setStudyEntity(studyEntity);

        return studyApplyEntity;
    }
    public static StudyApplyEntity toStudyApplyUpdateEntity(StudyUpdateDTO studyUpdateDTO, MemberEntity memberEntity,
                                                            StudyEntity studyEntity){
        StudyApplyEntity studyApplyEntity = new StudyApplyEntity();

        studyApplyEntity.setId(studyUpdateDTO.getStudyId());

        studyApplyEntity.setMemberEntity(memberEntity);
        studyApplyEntity.setStudyEntity(studyEntity);

        studyApplyEntity.setApplyState(true);


        return studyApplyEntity;
    }


}

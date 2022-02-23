package com.phl.cocolo.dto;

import com.phl.cocolo.entity.MemberEntity;
import com.phl.cocolo.entity.StudyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyDetailDTO {
    private Long studyId;
    private Long memberId;

    private int studyMax;
    private int studyCount;
    //작성자
    private String studyWriter;
    private String studyTitle;
    private String studyContents;
    private String studyPlace;
    private String studyCate;
    private String memberProfileName;

    public static StudyDetailDTO toStudyDetailDTO(StudyEntity studyEntity){
        StudyDetailDTO studyDetailDTO = new StudyDetailDTO();

        studyDetailDTO.setStudyId(studyEntity.getId());
        studyDetailDTO.setMemberId(studyEntity.getMemberEntity().getId());
        studyDetailDTO.setStudyWriter(studyEntity.getMemberEntity().getMemberNickName());

        studyDetailDTO.setStudyMax(studyEntity.getStudyMax());
        studyDetailDTO.setStudyCount(studyEntity.getStudyCount());
        studyDetailDTO.setStudyTitle(studyEntity.getStudyTitle());
        studyDetailDTO.setStudyContents(studyEntity.getStudyContents());
        studyDetailDTO.setStudyPlace(studyEntity.getStudyPlace());
        studyDetailDTO.setStudyCate(studyEntity.getStudyCate());
        studyDetailDTO.setMemberProfileName(studyEntity.getMemberEntity().getMemberProfileName());

        return studyDetailDTO;
    }





}

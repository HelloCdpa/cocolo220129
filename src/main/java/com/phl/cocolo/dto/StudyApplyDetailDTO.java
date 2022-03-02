package com.phl.cocolo.dto;

import com.phl.cocolo.entity.StudyApplyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyApplyDetailDTO {
    // 보여줄 것 : 내가 올린 스터디 글 제목, 신청한 회원의 닉네임, 개발자 레벨, 신청 날짜
    private Long studyApplyId;
    //신청한 회원 아이디
    private Long memberId;
    private Long studyId;

    //신청 날짜
    private LocalDateTime creatTime;

    private String memberNickName;

    //스터디 모집 제목
    private String studyTitle;

    //신청 승인 현황
    private boolean applyState;

    // 모집 인원 , 모집 가능 인원
    private int studyMax;
    private int studyCount;

    public static StudyApplyDetailDTO toStudyApplyDetailDTO(StudyApplyEntity studyApplyEntity){
        StudyApplyDetailDTO studyApplyDetailDTO = new StudyApplyDetailDTO();

        studyApplyDetailDTO.setMemberId(studyApplyEntity.getMemberEntity().getId());
        studyApplyDetailDTO.setStudyId(studyApplyEntity.getStudyEntity().getId());
        studyApplyDetailDTO.setCreatTime(studyApplyEntity.getCreatTime());
        studyApplyDetailDTO.setMemberNickName(studyApplyEntity.getMemberEntity().getMemberNickName());
        studyApplyDetailDTO.setStudyTitle(studyApplyEntity.getStudyEntity().getStudyTitle());
        studyApplyDetailDTO.setApplyState(studyApplyEntity.isApplyState());
        studyApplyDetailDTO.setStudyMax(studyApplyEntity.getStudyEntity().getStudyMax());
        studyApplyDetailDTO.setStudyCount(studyApplyEntity.getStudyEntity().getStudyCount());

        return studyApplyDetailDTO;
    }



}

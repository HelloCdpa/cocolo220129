package com.phl.cocolo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenteeSaveDTO {
    //신청한 멘티 회원번호
    private Long memberId;
    // 멘토링 글번호
    private Long mentoringId;
    // 멘토링 횟수
    private int menteeCount;

}

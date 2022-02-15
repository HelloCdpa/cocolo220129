package com.phl.cocolo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyUpdateDTO {
    private Long studyId;
    private Long memberId;

    private int studyMax;
    private int studyCount;

    private String studyTitle;
    private String studyContents;
    private String studyPlace;
    private String studyCate;

}

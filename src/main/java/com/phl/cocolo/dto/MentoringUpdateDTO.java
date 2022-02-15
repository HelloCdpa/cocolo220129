package com.phl.cocolo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentoringUpdateDTO {
    private Long mentoringId;
    private Long memberId;
    private Long cateId;

    private String mentoringTitle;
    private String mentoringContents;

    private int mentoringPrice;
    private String mentoringCareer;
    private String mentoringCate;
    private int mentoringCount;
}

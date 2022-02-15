package com.phl.cocolo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSaveDTO {
    private Long memberId;
    private Long onClassId;

    private int reviewStar;

    private String reviewContents;


}

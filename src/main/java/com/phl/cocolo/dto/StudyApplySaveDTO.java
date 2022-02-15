package com.phl.cocolo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyApplySaveDTO {
    private Long memberId;
    private Long studyId;

    private boolean applyState;

}

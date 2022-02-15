package com.phl.cocolo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenteeUpdateDTO {
    private Long menteeId;
    private Long memberId;
    private Long mentoringId;
    private int menteeCount;

}

package com.phl.cocolo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointDetailDTO {
    private  Long pointId;
    private Long memberId;

    private int pointPoint;
    private LocalDateTime pointDate;

    private String pointType;
}

package com.phl.cocolo.dto;

import com.phl.cocolo.entity.PointEntity;
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
    private String memberNickName;
    private Long memberPoint;

    public static PointDetailDTO toPointDetailDTO(PointEntity pointEntity){
        PointDetailDTO pointDetailDTO = new PointDetailDTO();

        pointDetailDTO.setPointId(pointEntity.getId());
        pointDetailDTO.setMemberId(pointEntity.getMemberEntity().getId());
        pointDetailDTO.setMemberNickName(pointEntity.getMemberEntity().getMemberNickName());
        pointDetailDTO.setMemberPoint(pointEntity.getMemberEntity().getMemberPoint());

        pointDetailDTO.setPointPoint(pointEntity.getPointPoint());
        pointDetailDTO.setPointDate(pointEntity.getPointDate());
        pointDetailDTO.setPointType(pointEntity.getPointType());

        return pointDetailDTO;

    }
}

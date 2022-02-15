package com.phl.cocolo.entity;

import com.phl.cocolo.dto.PointSaveDTO;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "point_table")
public class PointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @Column(columnDefinition = "integer default 0")
    private int pointPoint;

    @Column
    private LocalDateTime pointDate;

    @Column
    private String pointType;


    public static PointEntity toPointSaveEntity(PointSaveDTO pointSaveDTO,MemberEntity memberEntity){
        PointEntity pointEntity = new PointEntity();

        pointEntity.setMemberEntity(memberEntity);

        pointEntity.setPointPoint(pointSaveDTO.getPointPoint());
        pointEntity.setPointDate(LocalDateTime.now());
        pointEntity.setPointType(pointSaveDTO.getPointType());


        return pointEntity;
    }
}

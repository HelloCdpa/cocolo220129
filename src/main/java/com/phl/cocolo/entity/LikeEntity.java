package com.phl.cocolo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "like_table")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    public static LikeEntity toLikeEntity(MemberEntity memberEntity, BoardEntity boardEntity){
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setMemberEntity(memberEntity);
        likeEntity.setBoardEntity(boardEntity);

        return likeEntity;
    }


}

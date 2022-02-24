package com.phl.cocolo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "kakao_table")
public class KakaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kakao_id")
    private Long id;

    private String memberEmail;

    public static KakaoEntity toKakaoEntity(String memberEmail){
        KakaoEntity kakaoEntity = new KakaoEntity();
        kakaoEntity.setMemberEmail(memberEmail);

        return kakaoEntity;
    }
}

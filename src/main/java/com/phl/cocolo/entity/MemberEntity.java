package com.phl.cocolo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberNickname;

    @Column
    private String memberName;

    @Column
    private String memberPhone;

    @Column
    private String memberInterest;

    @Column
    private String memberProfileName;

    @Column
    private Long memberPoint;

    @Column
    private String memberLevel;










}

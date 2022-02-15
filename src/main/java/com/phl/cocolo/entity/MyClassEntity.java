package com.phl.cocolo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "my_class_table")
public class MyClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "myClass_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "onclass_id")
    private OnClassEntity onClassEntity;


    public static MyClassEntity toMyClassSaveEntity(MemberEntity memberEntity, OnClassEntity onClassEntity){
        MyClassEntity myClassEntity = new MyClassEntity();

        myClassEntity.setMemberEntity(memberEntity);
        myClassEntity.setOnClassEntity(onClassEntity);

        return myClassEntity;
    }
}

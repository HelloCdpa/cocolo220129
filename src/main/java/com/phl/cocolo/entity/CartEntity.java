package com.phl.cocolo.entity;

import com.phl.cocolo.dto.CartSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "cart_table")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "onClass_id")
    private OnClassEntity onClassEntity;


    public static CartEntity toCartSaveEntity(MemberEntity memberEntity,
                                              OnClassEntity onClassEntity){
        CartEntity cartEntity = new CartEntity();

        cartEntity.setMemberEntity(memberEntity);
        cartEntity.setOnClassEntity(onClassEntity);

        return cartEntity;

    }




}

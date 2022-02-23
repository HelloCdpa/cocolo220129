package com.phl.cocolo.dto;

import com.phl.cocolo.entity.MemberEntity;
import com.phl.cocolo.entity.OnClassEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartSaveDTO {
    private Long memberId;
    private Long onClassId;
    private int cartCount;



}

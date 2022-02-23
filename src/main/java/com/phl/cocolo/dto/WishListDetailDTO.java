package com.phl.cocolo.dto;

import com.phl.cocolo.entity.WishListEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListDetailDTO {

    private Long wishListId;
    private Long memberId;
    private Long onClassId;

    private String onClassTitle;
    private int onClassPrice;
    private String onClassFileName;

    public static WishListDetailDTO toWishListDetailDTO(WishListEntity wishListEntity){
        WishListDetailDTO wishListDetailDTO = new WishListDetailDTO();
        wishListDetailDTO.setWishListId(wishListEntity.getId());
        wishListDetailDTO.setMemberId(wishListEntity.getMemberEntity().getId());
        wishListDetailDTO.setOnClassId(wishListEntity.getOnClassEntity().getId());

        wishListDetailDTO.setOnClassTitle(wishListEntity.getOnClassEntity().getOnClassTitle());
        wishListDetailDTO.setOnClassPrice(wishListEntity.getOnClassEntity().getOnClassPrice());
        wishListDetailDTO.setOnClassFileName(wishListEntity.getOnClassEntity().getOnClassFileName());

        return wishListDetailDTO;
    }

}

package com.phl.cocolo.dto;

import com.phl.cocolo.entity.CartEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDTO {
    private Long cartId;
    private Long memberId;
    private Long onClassId;

    // 장바구니에서 보여줄 것 : 온클래스 제목/ 온클래스 사진/ 가격 / 장바구니 리스트의 가격
    private String onClassTitle;
    private int onClassPrice;
    private String onClassFileName;


    public static CartDetailDTO toCartDetailDTO(CartEntity cartEntity){
        CartDetailDTO cartDetailDTO = new CartDetailDTO();

        cartDetailDTO.setCartId(cartEntity.getId());

        cartDetailDTO.setMemberId(cartEntity.getMemberEntity().getId());
        cartDetailDTO.setOnClassId(cartEntity.getOnClassEntity().getId());

        cartDetailDTO.setOnClassTitle(cartEntity.getOnClassEntity().getOnClassTitle());
        cartDetailDTO.setOnClassPrice(cartEntity.getOnClassEntity().getOnClassPrice());
        cartDetailDTO.setOnClassFileName(cartEntity.getOnClassEntity().getOnClassFileName());
        return cartDetailDTO;
    }


}

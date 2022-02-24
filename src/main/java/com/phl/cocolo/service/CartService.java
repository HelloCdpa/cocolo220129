package com.phl.cocolo.service;

import com.phl.cocolo.dto.CartDetailDTO;
import com.phl.cocolo.dto.CartSaveDTO;

import java.util.List;

public interface CartService {
    boolean checkCart(CartSaveDTO cartSaveDTO);

    Long save(CartSaveDTO cartSaveDTO);

    List<CartDetailDTO> findByMemberId(Long memberId);

    void deleteById(Long cartId);

}

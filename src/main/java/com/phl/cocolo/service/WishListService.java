package com.phl.cocolo.service;

import com.phl.cocolo.dto.CartDetailDTO;
import com.phl.cocolo.dto.WishListDetailDTO;
import com.phl.cocolo.dto.WishListSaveDTO;

import java.util.List;

public interface WishListService {
    boolean checkWish(WishListSaveDTO wishListSaveDTO);

    void save(WishListSaveDTO wishListSaveDTO);

    List<WishListDetailDTO> findByMemberId(Long memberId);

    void deleteById(Long wishId);

    void deleteWish(WishListSaveDTO wishListSaveDTO);
}

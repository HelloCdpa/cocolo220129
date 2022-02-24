package com.phl.cocolo.service;

import com.phl.cocolo.dto.CartDetailDTO;
import com.phl.cocolo.dto.WishListDetailDTO;
import com.phl.cocolo.dto.WishListSaveDTO;
import com.phl.cocolo.entity.CartEntity;
import com.phl.cocolo.entity.MemberEntity;
import com.phl.cocolo.entity.OnClassEntity;
import com.phl.cocolo.entity.WishListEntity;
import com.phl.cocolo.repository.MemberRepository;
import com.phl.cocolo.repository.OnClassRepository;
import com.phl.cocolo.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService{
    private final WishListRepository wr;
    private final MemberRepository mr;
    private final OnClassRepository or;
    @Override
    public boolean checkWish(WishListSaveDTO wishListSaveDTO) {
        Optional<WishListEntity> checkWish = wr.findByMemberEntity_IdAndOnClassEntity_Id(wishListSaveDTO.getMemberId(), wishListSaveDTO.getOnClassId());
        if(checkWish.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public void save(WishListSaveDTO wishListSaveDTO) {
        MemberEntity memberEntity = mr.findById(wishListSaveDTO.getMemberId()).get();
        OnClassEntity onClassEntity = or.findById(wishListSaveDTO.getOnClassId()).get();
        wr.save(WishListEntity.toWishListSaveEntity(memberEntity,onClassEntity));
    }

    @Override
    public List<WishListDetailDTO> findByMemberId(Long memberId) {
        List<WishListEntity> wishListEntities= wr.findByMemberEntity_Id(memberId);
        List<WishListDetailDTO> wishList = new ArrayList<>();

        for (WishListEntity w : wishListEntities){
            wishList.add(WishListDetailDTO.toWishListDetailDTO(w));
        }
        return wishList;
    }

    @Override
    public void deleteById(Long wishId) {
        wr.deleteById(wishId);
    }

    @Override
    public void deleteWish(WishListSaveDTO wishListSaveDTO) {
        Long memberId = wishListSaveDTO.getMemberId();
        Long onClassId = wishListSaveDTO.getOnClassId();
        wr.deleteByMemberEntity_IdAndOnClassEntity_Id(memberId,onClassId);
    }
}

package com.phl.cocolo.service;

import com.phl.cocolo.dto.CartDetailDTO;
import com.phl.cocolo.dto.CartSaveDTO;
import com.phl.cocolo.entity.CartEntity;
import com.phl.cocolo.entity.MemberEntity;
import com.phl.cocolo.entity.OnClassEntity;
import com.phl.cocolo.repository.CartRepository;
import com.phl.cocolo.repository.MemberRepository;
import com.phl.cocolo.repository.OnClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{
    private final CartRepository cr;
    private final MemberRepository mr;
    private final OnClassRepository or;

    @Override
    public boolean checkCart(CartSaveDTO cartSaveDTO) {
         Optional<CartEntity> checkCart = cr.findByMemberEntity_IdAndOnClassEntity_Id(cartSaveDTO.getMemberId(), cartSaveDTO.getOnClassId());
         if(checkCart.isEmpty()){
             return true;
         }
         return false;
    }

    @Override
    public Long save(CartSaveDTO cartSaveDTO) {
        MemberEntity memberEntity = mr.findById(cartSaveDTO.getMemberId()).get();
        OnClassEntity onClassEntity = or.findById(cartSaveDTO.getOnClassId()).get();
        return cr.save(CartEntity.toCartSaveEntity(cartSaveDTO,memberEntity,onClassEntity)).getId();
    }

    @Override
    public List<CartDetailDTO> findByMemberId(Long memberId) {
        List<CartEntity> cartEntityList= cr.findByMemberEntity_Id(memberId);
        List<CartDetailDTO> cartList = new ArrayList<>();

        for (CartEntity c : cartEntityList){
            cartList.add(CartDetailDTO.toCartDetailDTO(c));
        }
        return cartList;
    }

    @Override
    public void deleteById(Long cartId) {
        cr.deleteById(cartId);
    }


}

package com.phl.cocolo.controller;

import com.phl.cocolo.dto.CartDetailDTO;
import com.phl.cocolo.dto.CartSaveDTO;
import com.phl.cocolo.dto.MemberDetailDTO;
import com.phl.cocolo.service.CartService;
import com.phl.cocolo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cs;
    private final MemberService ms;


    @PostMapping("/save")
    public @ResponseBody boolean
    save(@ModelAttribute CartSaveDTO cartSaveDTO) {
//        장바구니에 onClassId 와 memberId가 없으면 저장되고 true 를 반환, 이미 저장했다면 false 를 반환
       if(cs.checkCart(cartSaveDTO)) {
           cs.save(cartSaveDTO);
           return true;
       } else {
           return false;
       }
    }

    @GetMapping("/{memberId}")
    public String findByMemberId(@PathVariable ("memberId") Long memberId, Model model) {
        List<CartDetailDTO> cartList = cs.findByMemberId(memberId);

        if (!cartList.isEmpty()) {
            int totalPrice = 0;
            for (CartDetailDTO c : cartList) {
                totalPrice += c.getOnClassPrice();
                System.out.println("c.getOnClassPrice() = " + c.getOnClassPrice() + ", totalPrice = " + totalPrice);
            }
            model.addAttribute("cartList", cartList);
            model.addAttribute("totalPrice", totalPrice);

            MemberDetailDTO member = ms.findById(memberId);
            int memberPoint = member.getMemberPoint();
            model.addAttribute("memberPoint", memberPoint);

        }
        return "/onClass/cart";
    }
    @DeleteMapping("/{cartId}")
    public ResponseEntity deleteById(@PathVariable("cartId") Long cartId) {
        cs.deleteById(cartId);
        return new ResponseEntity(HttpStatus.OK);
    }


}

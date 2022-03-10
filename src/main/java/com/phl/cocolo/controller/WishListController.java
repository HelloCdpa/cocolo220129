package com.phl.cocolo.controller;

import com.phl.cocolo.dto.WishListDetailDTO;
import com.phl.cocolo.dto.WishListSaveDTO;
import com.phl.cocolo.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.phl.cocolo.common.SessionConst.LOGIN_ID;

@Controller
@RequestMapping("/wish")
@RequiredArgsConstructor
public class WishListController {
    private final WishListService ws;

    //위시리스트 저장
    @PostMapping("/save")
    public @ResponseBody
    boolean
    save(@ModelAttribute WishListSaveDTO wishListSaveDTO) {
//        장바구니에 onClassId 와 memberId가 없으면 저장되고 true 를 반환, 이미 저장했다면 false 를 반환,삭제
        if(ws.checkWish(wishListSaveDTO)) {
            ws.save(wishListSaveDTO);
            return true;
        } else {
            ws.deleteWish(wishListSaveDTO);
            return false;
        }
    }

    //위시리스트 조회
    @GetMapping("/{memberId}")
    public String findByMemberId(@PathVariable ("memberId") Long memberId, Model model, HttpSession session){
        List<WishListDetailDTO> wishList = ws.findByMemberId(memberId);
        model.addAttribute("wishList",wishList);
        //비었는지 조회
        boolean wishCheck = false;
        if(wishList.isEmpty()){
            wishCheck = true;
        }
        model.addAttribute("wishCheck", wishCheck);

        return "/onClass/wishList";
    }
    // 삭제
    @DeleteMapping("/{wishId}")
    public ResponseEntity deleteById(@PathVariable("wishId") Long wishId) {
        ws.deleteById(wishId);
        return new ResponseEntity(HttpStatus.OK);
    }
}

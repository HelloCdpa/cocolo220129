package com.phl.cocolo.controller;

import com.phl.cocolo.dto.*;
import com.phl.cocolo.service.CartService;
import com.phl.cocolo.service.OnClassService;
import com.phl.cocolo.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.phl.cocolo.common.SessionConst.LOGIN_ID;

@Controller
@RequestMapping("/onClass")
@RequiredArgsConstructor
public class OnClassController {
    private final OnClassService os;
    private final WishListService ws;

    @GetMapping("/save")
    public String saveForm() {
        return "/onClass/save";
    }
    //게시글 저장
    @PostMapping("/save")
    public String save(@Validated @ModelAttribute OnClassSaveDTO onClassSaveDTO)throws IllegalStateException, IOException {

        os.save(onClassSaveDTO);

        return "redirect:/onClass/";
    }
    // 상세조회
    @GetMapping("/{onClassId}")
    public String findById(@PathVariable("onClassId") Long onClassId, Model model){
        OnClassDetailDTO onClassDetailDTO = os.findById(onClassId);

        model.addAttribute("onClass",onClassDetailDTO);

        return "/onClass/findById";
    }

    //글 삭제
    @DeleteMapping("/{onClassId}")
    public ResponseEntity deleteById (@PathVariable ("onClassId") Long onClassId){
        os.deleteById(onClassId);
        return new ResponseEntity(HttpStatus.OK);
    }

    //전체조회
    @GetMapping("/")
    public String findAll(Model model, HttpSession session) {
        List<OnClassDetailDTO> onClassList = os.findAll();
        model.addAttribute("onClassList", onClassList);
        Long memberId = (Long) session.getAttribute(LOGIN_ID);

        model.addAttribute("wishCheck", ws.findByMemberId(memberId));

        return "/onClass/findAll";
    }

    // 수정 화면 이동
    @GetMapping("/update/{onClassId}")
    public String updateForm(Model model, @PathVariable ("onClassId") Long onClassId){
        OnClassDetailDTO onClass = os.findById(onClassId);
        model.addAttribute("onClass",onClass);
        return "/onClass/update";
    }
    // 수정 하기
    @PutMapping("/{onClassId}")
    public ResponseEntity update(@ModelAttribute OnClassUpdateDTO onClassUpdateDTO) {
        os.update(onClassUpdateDTO);

        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("/payment")
    public ResponseEntity payment(@RequestParam(value="cartList[]") List<CartDetailDTO> cartList) {
        System.out.println("cartList = " + cartList);
        //    CartDetailDTO 에서 쓸 것
        //    Long memberId;
        //    Long onClassId;

        // 1. 포인트 사용 내역 넣기
        // 100포인트로 4000원짜리 물건을 3900원에 구매 : 포인트 내역에는 [-100포인트 /수강권 구매 사용 /회원번호]

        // 2. 회원에게 강의 권한 주기
        // 회원의 온라인 클래스 : : my_class_table  여기에 onClassId 와 memberId를 반복문을 이용해 넣음
        // member_id
        // onClass_id

        //0번에 있는 onClassID : cartList.get(0).getOnClassId();




        return new ResponseEntity(HttpStatus.OK);
    }









}

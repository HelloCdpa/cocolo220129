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














}

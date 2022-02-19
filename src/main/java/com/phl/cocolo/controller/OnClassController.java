package com.phl.cocolo.controller;

import com.phl.cocolo.dto.*;
import com.phl.cocolo.service.MentoringService;
import com.phl.cocolo.service.OnClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/onclass")
@RequiredArgsConstructor
public class OnClassController {
    private final OnClassService os;

    @GetMapping("/save")
    public String saveForm() {
        return "/onclass/save";
    }
    //게시글 저장
    @PostMapping("/save")
    public String save(@Validated @ModelAttribute OnClassSaveDTO onClassSaveDTO){

        os.save(onClassSaveDTO);

        return "redirect:/onclass/";
    }
    // 상세조회
    @GetMapping("{onClassId}")
    public String findById(@PathVariable("onClassId") Long onClassId, Model model){
        OnClassDetailDTO onClassDetailDTO = os.findById(onClassId);

        model.addAttribute("onClass",onClassDetailDTO);

        return "/onclass/findById";
    }

    //글 삭제
    @DeleteMapping("{onClassId}")
    public ResponseEntity deleteById (@PathVariable ("onClassId") Long onClassId){
        os.deleteById(onClassId);
        return new ResponseEntity(HttpStatus.OK);
    }

    //전체조회
    @GetMapping("/")
    public String findAll(Model model) {
        List<OnClassDetailDTO> onClassList = os.findAll();
        model.addAttribute("onClassList", onClassList);
        return "/onclass/findAll";
    }

    // 수정 화면 이동
    @GetMapping("/update/{onClassId}")
    public String updateForm(Model model, @PathVariable ("onClassId") Long onClassId){
        OnClassDetailDTO onClass = os.findById(onClassId);
        model.addAttribute("onClass",onClass);
        return "/onclass/update";
    }
    // 수정 하기
    @PutMapping("{onClassId}")
    public ResponseEntity update(@ModelAttribute OnClassUpdateDTO onClassUpdateDTO) {
        os.update(onClassUpdateDTO);

        return new ResponseEntity(HttpStatus.OK);
    }














}

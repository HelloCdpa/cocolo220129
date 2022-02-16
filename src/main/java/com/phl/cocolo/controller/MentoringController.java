package com.phl.cocolo.controller;

import com.phl.cocolo.dto.*;
import com.phl.cocolo.service.MentoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/mentoring")
@RequiredArgsConstructor
public class MentoringController {
    private final MentoringService mts;

    @GetMapping("/save")
    public String saveForm() {
        return "/mentoring/save";
    }
    //게시글 저장
    @PostMapping("/save")
    public String save(@Validated @ModelAttribute MentoringSaveDTO mentoringSaveDTO){

        mts.save(mentoringSaveDTO);

        return "redirect:/mentoring/findAll";
    }
    // 상세조회
    @GetMapping("{mentoringId}")
    public String findById(@PathVariable("mentoringId") Long mentoringId, Model model){
        MentoringDetailDTO mentoringDetailDTO = mts.findById(mentoringId);

        model.addAttribute("mentoring",mentoringDetailDTO);

        return "/mentoring/findById";
    }
















}

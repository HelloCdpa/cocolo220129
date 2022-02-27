package com.phl.cocolo.controller;

import com.phl.cocolo.dto.*;
import com.phl.cocolo.service.MentoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.phl.cocolo.common.SessionConst.LOGIN_ID;
import static com.phl.cocolo.common.SessionConst.LOGIN_NICKNAME;


@Controller
@RequestMapping("/mentoring")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MentoringController {
    private final MentoringService mts;

    //게시글 저장
    @PostMapping("/save")
    public String save(@Validated @ModelAttribute MentoringSaveDTO mentoringSaveDTO){

        mts.save(mentoringSaveDTO);

        return "redirect:/mentoring/";
    }
    // 상세조회
    @GetMapping("{mentoringId}")
    public String findById(@PathVariable("mentoringId") Long mentoringId, Model model){
        MentoringDetailDTO mentoringDetailDTO = mts.findById(mentoringId);

        model.addAttribute("mentoring",mentoringDetailDTO);

        return "/mentoring/findById";
    }


    @GetMapping("/mentorChat")
    public String mentorChatForm(Model model, HttpSession session) {
        Long memberId = (Long) session.getAttribute(LOGIN_ID);
        Long memberNickName = (Long) session.getAttribute(LOGIN_NICKNAME);
        model.addAttribute("memberId",memberId);
        model.addAttribute("memberNickName",memberNickName);
        return "/mentoring/mentorChat";
    }

    //글 삭제
    @DeleteMapping("{mentoringId}")
    public ResponseEntity deleteById (@PathVariable ("mentoringId") Long mentoringId){
        mts.deleteById(mentoringId);
        return new ResponseEntity(HttpStatus.OK);
    }

    //전체조회
    @GetMapping("/")
    public String findAll(Model model) {
        List<MentoringDetailDTO> mentoringList = mts.findAll();
        model.addAttribute("mentoringList", mentoringList);
        return "/mentoring/findAll";
    }

    // 수정 화면 이동
    @GetMapping("/update/{mentoringId}")
    public String updateForm(Model model, @PathVariable ("mentoringId") Long mentoringId){
        MentoringDetailDTO mentoring = mts.findById(mentoringId);
        model.addAttribute("mentoring",mentoring);
        return "/mentoring/update";
    }
    // 수정 하기
    @PutMapping("{mentoringId}")
    public ResponseEntity update(@ModelAttribute MentoringUpdateDTO mentoringUpdateDTO) {
        mts.update(mentoringUpdateDTO);

        return new ResponseEntity(HttpStatus.OK);
    }









}

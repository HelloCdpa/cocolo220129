package com.phl.cocolo.controller;

import com.phl.cocolo.dto.*;
import com.phl.cocolo.service.BoardService;
import com.phl.cocolo.service.OnClassService;
import com.phl.cocolo.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/study")
@RequiredArgsConstructor
public class StudyController {
    private final StudyService ss;
    @GetMapping("/save")
    public String saveForm() {
        return "/study/save";
    }
    //게시글 저장
    @PostMapping("/save")
    public String save(@ModelAttribute StudySaveDTO studySaveDTO){

        ss.save(studySaveDTO);

        return "redirect:/study/";
    }
    // 상세조회
    @GetMapping("{studyId}")
    public String findById(@PathVariable("studyId") Long studyId, Model model){
        StudyDetailDTO study = ss.findById(studyId);

        model.addAttribute("study",study);

        return "/study/findById";
    }

    //글 삭제
    @DeleteMapping("{studyId}")
    public ResponseEntity deleteById (@PathVariable ("studyId") Long studyId){
        ss.deleteById(studyId);
        return new ResponseEntity(HttpStatus.OK);
    }

    //전체조회
    @GetMapping("/")
    public String findAll(Model model) {
        List<StudyDetailDTO> studyList = ss.findAll();
        model.addAttribute("studyList", studyList);
        return "/study/findAll";
    }

    // 수정 화면 이동
    @GetMapping("/update/{studyId}")
    public String updateForm(Model model, @PathVariable ("studyId") Long studyId){
        StudyDetailDTO study = ss.findById(studyId);
        model.addAttribute("study",study);
        return "/study/update";
    }
    // 수정 하기
    @PutMapping("/{studyId}")
    public ResponseEntity update(@ModelAttribute StudyUpdateDTO studyUpdateDTO) {
        ss.update(studyUpdateDTO);

        return new ResponseEntity(HttpStatus.OK);
    }


}

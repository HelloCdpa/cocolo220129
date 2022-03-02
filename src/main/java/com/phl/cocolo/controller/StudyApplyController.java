package com.phl.cocolo.controller;

import com.phl.cocolo.dto.StudyApplyDetailDTO;
import com.phl.cocolo.dto.StudyApplySaveDTO;
import com.phl.cocolo.service.StudyApplyService;
import com.phl.cocolo.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/studyApply")
@RequiredArgsConstructor
public class StudyApplyController {
    private final StudyApplyService sas;
    //스터디 신청
    @PostMapping("/")
    public @ResponseBody
    String studyApply(@ModelAttribute StudyApplySaveDTO studyApplySaveDTO){
        String result = sas.studyApplySave(studyApplySaveDTO);

        return result;
    }
    //스터디 신청 현황 화면
    @GetMapping("/myStudy/{memberId}")
    public String MyStudyApplyPage(@PathVariable("memberId") Long memberId, Model model){
        // 스터디 작성자
        List<StudyApplyDetailDTO> writerApplyList= sas.findByStudyApplyId(memberId);
        model.addAttribute("writerApplyList",writerApplyList);

        //스터디 신청자
        List<StudyApplyDetailDTO>  applyList = sas.myApplyList(memberId);
        model.addAttribute("applyList",applyList);



        return "/study/myStudy";
    }

    @PutMapping("/{studyApplyId}")
    public ResponseEntity studyApply(@PathVariable("studyApplyId") Long studyApplyId){
        sas.updateByApplyState(studyApplyId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{studyApplyId}")
    public ResponseEntity studyApplyDelete(@PathVariable("studyApplyId") Long studyApplyId){
        sas.deleteById(studyApplyId);

        return new ResponseEntity(HttpStatus.OK);
    }

}

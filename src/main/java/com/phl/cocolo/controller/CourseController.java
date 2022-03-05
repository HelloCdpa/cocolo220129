package com.phl.cocolo.controller;

import com.phl.cocolo.dto.*;
import com.phl.cocolo.service.CourseService;
import com.phl.cocolo.service.OnClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService cs;
    private final OnClassService os;

    @GetMapping("/save")
    public String saveForm() {
        return "/course/save";
    }
    //게시글 저장
    @PostMapping("/save")
    public String save(@Validated @ModelAttribute CourseSaveDTO courseSaveDTO)throws IllegalStateException, IOException {

        cs.save(courseSaveDTO);

        return "redirect:/onClass/"+courseSaveDTO.getOnClassId();
    }
    // 상세조회
    @GetMapping("/{courseId}")
    public String findById(@PathVariable("courseId") Long courseId, Model model){
        CourseDetailDTO courseDetailDTO = cs.findById(courseId);

        model.addAttribute("course",courseDetailDTO);

        return "/course/findById";
    }


    //글 삭제
    @DeleteMapping("{courseId}")
    public ResponseEntity deleteById (@PathVariable ("courseId") Long courseId){
        cs.deleteById(courseId);
        return new ResponseEntity(HttpStatus.OK);
    }

    //전체조회
    @GetMapping("/{onClassId}")
    public String findAll(Model model,@PathVariable ("onClassId") Long onClassId) {
        List<CourseDetailDTO> courseList = cs.findAll(onClassId);
        model.addAttribute("courseList", courseList);
        return "/course/findAll";
    }

    // 수정 화면 이동
    @GetMapping("/update/{courseId}")
    public String updateForm(Model model, @PathVariable ("courseId") Long courseId){
        CourseDetailDTO course = cs.findById(courseId);
        model.addAttribute("course",course);
        return "/course/update";
    }
    // 수정 하기
    @PutMapping("{courseId}")
    public ResponseEntity update(@ModelAttribute CourseUpdateDTO courseUpdateDTO) {
        cs.update(courseUpdateDTO);

        return new ResponseEntity(HttpStatus.OK);
    }









}

package com.phl.cocolo.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phl.cocolo.dto.*;
import com.phl.cocolo.service.*;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.phl.cocolo.common.SessionConst.LOGIN_ID;

@Controller
@RequestMapping("/onClass")
@RequiredArgsConstructor
public class OnClassController {
    private final OnClassService os;
    private final WishListService ws;
    private final MemberService ms;
    private final ReviewService rs;
    private final CourseService cs;



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

        List<ReviewDetailDTO> reviewList = rs.findAll(onClassId);
        model.addAttribute("reviewList",reviewList);

        List<CourseDetailDTO> courseList = cs.findAll(onClassId);
        model.addAttribute("courseList", courseList);
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



    // 온라인 클래스 구매 적용하기
    @PostMapping("/pointPayment")
    public ResponseEntity pointPayment(@RequestParam ("memberId") Long memberId,@RequestParam ("pointPoint") int pointPoint ) {
        // 1. 사용한 포인트가 0보다 크다면, 포인트 사용 내역 넣기
        if(0<pointPoint) {
            PointSaveDTO pointSaveDTO = new PointSaveDTO(memberId, -pointPoint, "수강권 구매 사용");
            ms.pointPayment(pointSaveDTO);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/payment")
    public String payment(@RequestParam ("onClassId") Long onClassId,HttpSession session){
         // 2. 회원에게 강의 권한 주기 // 3. 장바구니 비우기

        Long memberId = (Long) session.getAttribute(LOGIN_ID);
        os.payment(onClassId,memberId);

        return "redirect:/onClass/"+memberId;

    }










}

package com.phl.cocolo.controller;

import com.phl.cocolo.dto.ReviewDetailDTO;
import com.phl.cocolo.dto.ReviewSaveDTO;
import com.phl.cocolo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService rs;

    @PostMapping("/save")
    public @ResponseBody
    List<ReviewDetailDTO> save(@ModelAttribute ReviewSaveDTO ReviewSaveDTO, Model model) {
        //저장
        rs.save(ReviewSaveDTO);
        //리스트로 전체 댓글 가져오기
        List<ReviewDetailDTO> reviewList = rs.findAll(ReviewSaveDTO.getOnClassId());
        model.addAttribute("reviewList", reviewList);
        return reviewList;
    }

    @GetMapping("/{onClassId}")
    public @ResponseBody
    List<ReviewDetailDTO> findAll(@PathVariable("onClassId") Long onClassId, Model model, @ModelAttribute ReviewDetailDTO ReviewDetailDTO) {
        List<ReviewDetailDTO> reviewList = rs.findAll(onClassId);
        model.addAttribute("reviewList", reviewList);
        return reviewList;
    }


    @DeleteMapping("/{reviewId}")
    public ResponseEntity deleteById(@PathVariable("reviewId") Long reviewId) {
        System.out.println("reviewId = " + reviewId);
        rs.deleteById(reviewId);

        return new ResponseEntity(HttpStatus.OK);
    }


}



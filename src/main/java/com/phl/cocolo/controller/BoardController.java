package com.phl.cocolo.controller;

import com.phl.cocolo.common.PagingConst;
import com.phl.cocolo.dto.*;
import com.phl.cocolo.service.BoardService;
import com.phl.cocolo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService bs;
    private final CommentService cs;
    //게시글 저장 화면 이동
    @GetMapping("/save")
    public String saveForm(Model model){
        model.addAttribute("board",new BoardSaveDTO());
        List<CategoryDetailDTO> categoryList = bs.cateFindAll();
        model.addAttribute("categoryList",categoryList);
        return "/board/save";
    }
    //게시글 저장
    @PostMapping("/save")
    public String save(@Validated @ModelAttribute BoardSaveDTO boardSaveDTO, BindingResult bindingResult)throws IllegalStateException, IOException {
        System.out.println("받아온 값"+boardSaveDTO);
        if (bindingResult.hasErrors()) {
            return "/board/save";
        }
        bs.save(boardSaveDTO);

        return "redirect:/board/";
    }
    // 상세조회
    @GetMapping("{boardId}")
    public String findById(@PathVariable("boardId") Long boardId, Model model){
        BoardDetailDTO boardDetailDTO = bs.findById(boardId);
        List<CommentDetailDTO> commentList = cs.findAll(boardId);
        model.addAttribute("board",boardDetailDTO);
        model.addAttribute("commentList",commentList);
        return "/board/findById";
    }
    // 전체 조회 (페이징 처리)
    @GetMapping
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model){
        Page<BoardPagingDTO> boardList = bs.paging(pageable);

        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT-1)< boardList.getTotalPages())?startPage + PagingConst.BLOCK_LIMIT -1 : boardList.getTotalPages();
        List<CategoryDetailDTO> categoryList = bs.cateFindAll();

        model.addAttribute("boardList",boardList);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        model.addAttribute("categoryList",categoryList);

        return "/board/findAll";

    }

    //관리자가 카테고리 저장
    @GetMapping("/cateSave")
    public String cateSave(@ModelAttribute CategorySaveDTO categorySaveDTO) {

        bs.cateSave(categorySaveDTO);

        return "redirect:/board/findAll";
    }

    //카테고리 별로 조회(페이징 처리)
    @GetMapping("/cateBoard/{cateId}")
    public String cateBoardForm(@PathVariable ("cateId") Long cateId, Model model,
                                @PageableDefault(page = 1)Pageable pageable) {
        Page<BoardPagingDTO> boardList =  bs.findCate(cateId, pageable);


        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT-1)< boardList.getTotalPages())?startPage + PagingConst.BLOCK_LIMIT -1 : boardList.getTotalPages();
        List<CategoryDetailDTO> categoryList = bs.cateFindAll();

        model.addAttribute("boardList",boardList);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        model.addAttribute("categoryList",categoryList);

        return "/board/findAll";
    }
    //검색결과 별로 조회(페이징 처리)
    @GetMapping("/search")
    public String search(@RequestParam("type") String type,
                         @RequestParam("keyword") String keyword, Model model,
                         @PageableDefault(page = 1)Pageable pageable) {
        Page<BoardPagingDTO> boardList = bs.search(type, keyword, pageable);

        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT-1)< boardList.getTotalPages())?startPage + PagingConst.BLOCK_LIMIT -1 : boardList.getTotalPages();
        List<CategoryDetailDTO> categoryList = bs.cateFindAll();

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);

        model.addAttribute("categoryList",categoryList);
        return "/board/findAll";
    }





    // 수정 화면 이동
    @GetMapping("/update/{boardId}")
    public String updateForm(Model model, @PathVariable ("boardId") Long boardId){
        BoardDetailDTO board = bs.findById(boardId);
        model.addAttribute("board",board);
        return "/board/update";
    }
    // 수정 하기
    @PutMapping("{boardId}")
    public ResponseEntity update(@ModelAttribute BoardUpdateDTO boardUpdateDTO)  throws IllegalStateException, IOException {
        bs.update(boardUpdateDTO);

        return new ResponseEntity(HttpStatus.OK);
    }
    //게시글 삭제
    @DeleteMapping("{boardId}")
    public ResponseEntity deleteById (@PathVariable ("boardId") Long boardId){
        bs.deleteById(boardId);
        return new ResponseEntity(HttpStatus.OK);
    }







}

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

    @GetMapping("/save")
    public String saveForm(Model model){
        model.addAttribute("board",new BoardSaveDTO());
        return "/board/save";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute BoardSaveDTO boardSaveDTO, BindingResult bindingResult)throws IllegalStateException, IOException {
        System.out.println("받아온 값"+boardSaveDTO);
        if (bindingResult.hasErrors()) {
            return "/board/save";
        }
        Long boardId = bs.save(boardSaveDTO);

        return "redirect:/board/";
    }

    @GetMapping("{boardId}")
    public String findById(@PathVariable("boardId") Long boardId, Model model){
        BoardDetailDTO boardDetailDTO = bs.findById(boardId);
        List<CommentDetailDTO> commentList = cs.findAll(boardId);
        model.addAttribute("board",boardDetailDTO);
        model.addAttribute("commentList",commentList);
        return "/board/findById";
    }

    @GetMapping
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model){
        Page<BoardPagingDTO> boardList = bs.paging(pageable);
        model.addAttribute("boardList",boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT-1)< boardList.getTotalPages())?startPage + PagingConst.BLOCK_LIMIT -1 : boardList.getTotalPages();
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return "/board/findAll";

    }

    @GetMapping("/update/{boardId}")
    public String updateForm(Model model, @PathVariable ("boardId") Long boardId){
        BoardDetailDTO board = bs.findById(boardId);
        model.addAttribute("board",board);
        return "/board/update";
    }

    @PutMapping("{boardId}")
    public ResponseEntity update(@ModelAttribute BoardUpdateDTO boardUpdateDTO)  throws IllegalStateException, IOException {
        bs.update(boardUpdateDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("{boardId}")
    public ResponseEntity deleteById (@PathVariable ("boardId") Long boardId){
        bs.deleteById(boardId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search")
    public String search(@RequestParam("type") String type,
                         @RequestParam("keyword") String keyword, Model model,
                         @PageableDefault(page = 1)Pageable pageable) {
        Page<BoardPagingDTO> boardList = bs.search(type, keyword, pageable);

        model.addAttribute("boardList", boardList);

        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT-1)< boardList.getTotalPages())?startPage + PagingConst.BLOCK_LIMIT -1 : boardList.getTotalPages();
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);

        return "/board/findAll";
    }





}

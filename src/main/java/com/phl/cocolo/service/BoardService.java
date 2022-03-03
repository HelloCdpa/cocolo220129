package com.phl.cocolo.service;

import com.phl.cocolo.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    Long save(BoardSaveDTO boardSaveDTO)throws IllegalStateException, IOException;

    BoardDetailDTO findById(Long boardId);

    Page<BoardPagingDTO> paging(Pageable pageable);

    Long update(BoardUpdateDTO boardUpdateDTO) throws IllegalStateException, IOException ;

    void deleteById(Long boardId);

    Page<BoardPagingDTO> search(String type, String keyword,Pageable pageable);

    Long saveTest(BoardSaveDTO boardSaveDTO);


    Long cateSave(CategorySaveDTO categorySaveDTO);

    Page<BoardPagingDTO> findCate(Long cateId, Pageable pageable);

    List<CategoryDetailDTO> cateFindAll();

    int findLike(Long boardId, Long memberId);

    int saveLike(Long boardId, Long memberId);

}

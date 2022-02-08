package com.phl.cocolo.service;

import com.phl.cocolo.dto.BoardDetailDTO;
import com.phl.cocolo.dto.BoardPagingDTO;
import com.phl.cocolo.dto.BoardSaveDTO;
import com.phl.cocolo.dto.BoardUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface BoardService {
    Long save(BoardSaveDTO boardSaveDTO)throws IllegalStateException, IOException;

    BoardDetailDTO findById(Long boardId);

    Page<BoardPagingDTO> paging(Pageable pageable);

    Long update(BoardUpdateDTO boardUpdateDTO) throws IllegalStateException, IOException ;

    void deleteById(Long boardId);

    Page<BoardPagingDTO> search(String type, String keyword,Pageable pageable);

    Long saveTest(BoardSaveDTO boardSaveDTO);


}

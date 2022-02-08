package com.phl.cocolo.service;

import com.phl.cocolo.dto.CommentDetailDTO;
import com.phl.cocolo.dto.CommentSaveDTO;

import java.util.List;

public interface CommentService {
    Long save(CommentSaveDTO commentSaveDTO);

    List<CommentDetailDTO> findAll(Long boardId);

    void deleteById(Long commentId);
}

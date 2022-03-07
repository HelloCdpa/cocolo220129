package com.phl.cocolo.service;

import com.phl.cocolo.dto.ReviewDetailDTO;
import com.phl.cocolo.dto.ReviewSaveDTO;

import java.util.List;

public interface ReviewService {
    Long save(ReviewSaveDTO reviewSaveDTO);

    List<ReviewDetailDTO> findAll(Long onClassId);

    void deleteById(Long reviewId);

    boolean reviewCheck(Long memberId, Long onClassId);
}

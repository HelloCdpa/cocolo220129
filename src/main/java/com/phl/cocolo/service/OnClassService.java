package com.phl.cocolo.service;

import com.phl.cocolo.dto.OnClassDetailDTO;
import com.phl.cocolo.dto.OnClassSaveDTO;

import java.io.IOException;
import java.util.List;

public interface OnClassService {
    Long save(OnClassSaveDTO onClassSaveDTO) throws IllegalStateException, IOException;

    Long saveTest(OnClassSaveDTO onClassSaveDTO);

    OnClassDetailDTO findById(Long onClassId);

    void deleteById(Long onClassId);

    List<OnClassDetailDTO> findAll();

    void payment(Long onClassId,Long memberId);
}

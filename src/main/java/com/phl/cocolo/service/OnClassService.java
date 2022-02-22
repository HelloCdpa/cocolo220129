package com.phl.cocolo.service;

import com.phl.cocolo.dto.OnClassDetailDTO;
import com.phl.cocolo.dto.OnClassSaveDTO;
import com.phl.cocolo.dto.OnClassUpdateDTO;

import java.io.IOException;
import java.util.List;

public interface OnClassService {
    Long save(OnClassSaveDTO onClassSaveDTO) throws IllegalStateException, IOException;

    OnClassDetailDTO findById(Long onClassId);

    void deleteById(Long onClassId);

    List<OnClassDetailDTO> findAll();

    void update(OnClassUpdateDTO onClassUpdateDTO);
}

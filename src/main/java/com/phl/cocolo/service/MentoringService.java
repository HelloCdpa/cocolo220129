package com.phl.cocolo.service;

import com.phl.cocolo.dto.MentoringDetailDTO;
import com.phl.cocolo.dto.MentoringSaveDTO;
import com.phl.cocolo.dto.MentoringUpdateDTO;

import java.util.List;

public interface MentoringService {
    Long save(MentoringSaveDTO mentoringSaveDTO);

    MentoringDetailDTO findById(Long mentoringId);

    void deleteById(Long mentoringId);

    List<MentoringDetailDTO> findAll();

    void update(MentoringUpdateDTO mentoringUpdateDTO);
}

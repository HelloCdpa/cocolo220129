package com.phl.cocolo.service;

import com.phl.cocolo.dto.MentoringDetailDTO;
import com.phl.cocolo.dto.MentoringSaveDTO;

public interface MentoringService {
    Long save(MentoringSaveDTO mentoringSaveDTO);

    MentoringDetailDTO findById(Long mentoringId);
}

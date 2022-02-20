package com.phl.cocolo.service;

import com.phl.cocolo.dto.StudyDetailDTO;
import com.phl.cocolo.dto.StudySaveDTO;
import com.phl.cocolo.dto.StudyUpdateDTO;

import java.util.List;

public interface StudyService {
    void save(StudySaveDTO studySaveDTO);

    StudyDetailDTO findById(Long studyId);

    void deleteById(Long studyId);

    List<StudyDetailDTO> findAll();

    void update(StudyUpdateDTO studyUpdateDTO);
}

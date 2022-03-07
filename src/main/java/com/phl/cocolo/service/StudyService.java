package com.phl.cocolo.service;

import com.phl.cocolo.dto.*;

import java.util.List;

public interface StudyService {
    void save(StudySaveDTO studySaveDTO);

    StudyDetailDTO findById(Long studyId);

    void deleteById(Long studyId);

    List<StudyDetailDTO> findAll();

    void update(StudyUpdateDTO studyUpdateDTO);


    List<StudyDetailDTO> findAllByMemberId(Long memberId);
}

package com.phl.cocolo.service;

import com.phl.cocolo.dto.StudyApplyDetailDTO;
import com.phl.cocolo.dto.StudyApplySaveDTO;

import java.util.List;

public interface StudyApplyService {
    String studyApplySave(StudyApplySaveDTO studyApplySaveDTO);
    List<StudyApplyDetailDTO> findByStudyApplyId(Long memberId);

    List<StudyApplyDetailDTO> myApplyList(Long memberId);


    void updateByApplyState(Long studyApplyId);

    void deleteById(Long studyApplyId);
}

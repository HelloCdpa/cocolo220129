package com.phl.cocolo.service;

import com.phl.cocolo.dto.*;

import java.util.List;

public interface MentoringService {
    Long save(MentoringSaveDTO mentoringSaveDTO);

    MentoringDetailDTO findById(Long mentoringId);

    void deleteById(Long mentoringId);

    List<MentoringDetailDTO> findAll();

    void update(MentoringUpdateDTO mentoringUpdateDTO);

    void saveMentee(MenteeSaveDTO menteeSaveDTO);

    List<MenteeDetailDTO> findAllByMemberId(Long memberId);

    List<MenteeDetailDTO> fundAllMentorMemberId(Long memberId);

    void updateCount(Long menteeId);

    MenteeDetailDTO findByMenteeId(Long menteeId);

    List<MentoringDetailDTO> mentorFindAllByMemberId(Long memberId);
}

package com.phl.cocolo.service;

import com.phl.cocolo.dto.MentoringDetailDTO;
import com.phl.cocolo.dto.MentoringSaveDTO;
import com.phl.cocolo.entity.MemberEntity;
import com.phl.cocolo.entity.MentoringEntity;
import com.phl.cocolo.repository.MemberRepository;
import com.phl.cocolo.repository.MentoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MentoringServiceImpl implements MentoringService{
    private final MentoringRepository mtr;
    private final MemberRepository mr;

    @Override
    public Long save(MentoringSaveDTO mentoringSaveDTO) {
        MemberEntity memberEntity = mr.findById(mentoringSaveDTO.getMemberId()).get();
        MentoringEntity mentoringEntity = MentoringEntity.toMentoringSaveEntity(mentoringSaveDTO,memberEntity);

        return mtr.save(mentoringEntity).getId();
    }

    @Override
    public MentoringDetailDTO findById(Long mentoringId) {
        return null;
    }
}

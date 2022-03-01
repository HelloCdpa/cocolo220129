package com.phl.cocolo.service;

import com.phl.cocolo.dto.*;
import com.phl.cocolo.entity.MemberEntity;
import com.phl.cocolo.entity.MenteeEntity;
import com.phl.cocolo.entity.MentoringEntity;
import com.phl.cocolo.repository.MemberRepository;
import com.phl.cocolo.repository.MenteeRepository;
import com.phl.cocolo.repository.MentoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MentoringServiceImpl implements MentoringService{
    private final MentoringRepository mtr;
    private final MemberRepository mr;
    private final MenteeRepository mer;

    @Override
    public Long save(MentoringSaveDTO mentoringSaveDTO) {
        MemberEntity memberEntity = mr.findById(mentoringSaveDTO.getMemberId()).get();
        MentoringEntity mentoringEntity = MentoringEntity.toMentoringSaveEntity(mentoringSaveDTO,memberEntity);

        return mtr.save(mentoringEntity).getId();
    }

    @Override
    public MentoringDetailDTO findById(Long mentoringId) {
        Optional<MentoringEntity> optionalMentoringEntity = mtr.findById(mentoringId);
        MentoringDetailDTO mentoringDetailDTO = MentoringDetailDTO.toMentoringDetailDTO(optionalMentoringEntity.get());

        return mentoringDetailDTO;
    }

    @Override
    public void deleteById(Long mentoringId) {
        mtr.deleteById(mentoringId);
    }

    @Override
    public List<MentoringDetailDTO> findAll() {
        List<MentoringEntity> mentoringEntityList = mtr.findAll();
        List<MentoringDetailDTO> mentoringList = new ArrayList<>();
        for (MentoringEntity m : mentoringEntityList){
            mentoringList.add(MentoringDetailDTO.toMentoringDetailDTO(m));
        }
        return mentoringList;
    }

    @Override
    public void update(MentoringUpdateDTO mentoringUpdateDTO) {
        MemberEntity memberEntity = mr.findById(mentoringUpdateDTO.getMemberId()).get();
        MentoringEntity mentoringEntity = MentoringEntity.toMentoringUpdateEntity(mentoringUpdateDTO,memberEntity);

        mtr.save(mentoringEntity);
    }

    @Override
    public void saveMentee(MenteeSaveDTO menteeSaveDTO) {
        MemberEntity memberEntity = mr.findById(menteeSaveDTO.getMemberId()).get();
        MentoringEntity mentoringEntity = mtr.findById(menteeSaveDTO.getMentoringId()).get();
        MenteeEntity menteeEntity = MenteeEntity.toMenteeSaveEntity(memberEntity,mentoringEntity);
        mer.save(menteeEntity);
    }

    @Override
    public List<MenteeDetailDTO> findAllByMemberId(Long memberId) {
        List<MenteeEntity> menteeEntityList = mer.findAllByMemberEntity_Id(memberId);
        List<MenteeDetailDTO> menteeDetailList = new ArrayList<>();

        for (MenteeEntity m : menteeEntityList){
            menteeDetailList.add(MenteeDetailDTO.toMenteeDetailDTO(m));
        }

        return menteeDetailList;
    }


}

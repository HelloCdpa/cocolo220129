package com.phl.cocolo.service;

import com.phl.cocolo.dto.*;
import com.phl.cocolo.entity.BoardEntity;
import com.phl.cocolo.entity.MemberEntity;
import com.phl.cocolo.entity.MenteeEntity;
import com.phl.cocolo.entity.MentoringEntity;
import com.phl.cocolo.repository.MemberMapperRepository;
import com.phl.cocolo.repository.MemberRepository;
import com.phl.cocolo.repository.MenteeRepository;
import com.phl.cocolo.repository.MentoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MentoringServiceImpl implements MentoringService{
    private final MentoringRepository mtr;
    private final MemberRepository mr;
    private final MenteeRepository mer;
    private final MemberService ms;


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
        List<MentoringEntity> mentoringEntityList = mtr.findAll(Sort.by(Sort.Direction.DESC, "id"));
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

    //멘티
    @Override
    public List<MenteeDetailDTO> findAllByMemberId(Long memberId) {
        List<MenteeEntity> menteeEntityList = mer.findAllByMemberEntity_Id(memberId);
        List<MenteeDetailDTO> menteeDetailList = new ArrayList<>();

        for (MenteeEntity m : menteeEntityList){
            menteeDetailList.add(MenteeDetailDTO.toMenteeDetailDTO(m));
        }

        return menteeDetailList;
    }
    //멘토
    @Override
    public List<MenteeDetailDTO> fundAllMentorMemberId(Long memberId) {
        List<MenteeEntity> mentorEntityList = mer.findAllByMentoringEntity_MemberEntity_Id(memberId);
        List<MenteeDetailDTO> menteeDetailList = new ArrayList<>();

        for (MenteeEntity m : mentorEntityList){
            menteeDetailList.add(MenteeDetailDTO.toMenteeDetailDTO(m));
        }

        return menteeDetailList;



    }

    @Override
    public void updateCount(Long menteeId) {
      mer.updateMenteeCount(menteeId);
      //멘토에게 포인트 적립
        MenteeDetailDTO menteeDetailDTO = MenteeDetailDTO.toMenteeDetailDTO(mer.findById(menteeId).get());
        PointSaveDTO pointSaveDTO = new PointSaveDTO(menteeDetailDTO.getMentorId(),menteeDetailDTO.getMentoringPrice(),"멘토링 포인트 적립");
        ms.pointCharge(pointSaveDTO);

    }

    @Override
    public MenteeDetailDTO findByMenteeId(Long menteeId) {
        MenteeDetailDTO menteeDetailDTO = MenteeDetailDTO.toMenteeDetailDTO(mer.findById(menteeId).get());
        return menteeDetailDTO;
    }

    @Override
    public List<MentoringDetailDTO> mentorFindAllByMemberId(Long memberId) {
        List<MentoringEntity> mentoringEntityList = mtr.findAllByMemberEntity_Id(memberId);
        List<MentoringDetailDTO> mentoringList = new ArrayList<>();

        for (MentoringEntity m: mentoringEntityList){
            mentoringList.add(MentoringDetailDTO.toMentoringDetailDTO(m));
        }



        return mentoringList;
    }


}

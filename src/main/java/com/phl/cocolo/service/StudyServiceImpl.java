package com.phl.cocolo.service;

import com.phl.cocolo.dto.*;
import com.phl.cocolo.entity.*;
import com.phl.cocolo.repository.MemberRepository;
import com.phl.cocolo.repository.StudyApplyRepository;
import com.phl.cocolo.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService{
    private final StudyRepository sr;
    private final MemberRepository mr;



    @Override
    public void save(StudySaveDTO studySaveDTO) {
        MemberEntity memberEntity = mr.findById(studySaveDTO.getMemberId()).get();
        sr.save(StudyEntity.toStudySaveEntity(studySaveDTO,memberEntity));
    }

    @Override
    public StudyDetailDTO findById(Long studyId) {
        Optional<StudyEntity> optionalStudyEntity = sr.findById(studyId);
        StudyDetailDTO studyDetailDTO = StudyDetailDTO.toStudyDetailDTO(optionalStudyEntity.get());
        return studyDetailDTO;
    }

    @Override
    public void deleteById(Long studyId) {
        sr.deleteById(studyId);
    }

    @Override
    public List<StudyDetailDTO> findAll() {
        List<StudyEntity> studyEntityList = sr.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<StudyDetailDTO> studyList = new ArrayList<>();

        for(StudyEntity s : studyEntityList){
            studyList.add(StudyDetailDTO.toStudyDetailDTO(s));
        }


        return studyList;
    }

    @Override
    public void update(StudyUpdateDTO studyUpdateDTO) {
        sr.save(StudyEntity.toStudyUpdateEntity(studyUpdateDTO,mr.findById(studyUpdateDTO.getMemberId()).get()));
    }

    @Override
    public List<StudyDetailDTO> findAllByMemberId(Long memberId) {
        List<StudyEntity> studyEntityList = sr.findAllByMemberEntity_Id(memberId);
        List<StudyDetailDTO> studyList = new ArrayList<>();

        for (StudyEntity s: studyEntityList){
            studyList.add(StudyDetailDTO.toStudyDetailDTO(s));
        }



        return studyList;
    }


}

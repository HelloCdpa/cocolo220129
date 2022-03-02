package com.phl.cocolo.service;

import com.phl.cocolo.dto.StudyApplyDetailDTO;
import com.phl.cocolo.dto.StudyApplySaveDTO;
import com.phl.cocolo.entity.MemberEntity;
import com.phl.cocolo.entity.StudyApplyEntity;
import com.phl.cocolo.entity.StudyEntity;
import com.phl.cocolo.repository.MemberRepository;
import com.phl.cocolo.repository.StudyApplyMapperRepository;
import com.phl.cocolo.repository.StudyApplyRepository;
import com.phl.cocolo.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyApplyServiceImpl implements StudyApplyService{
    private final StudyApplyRepository sar;
    private final StudyRepository sr;
    private final MemberRepository mr;
    private final StudyApplyMapperRepository samr;


    @Override
    public String studyApplySave(StudyApplySaveDTO studyApplySaveDTO) {
        long memberId = studyApplySaveDTO.getMemberId();
        long studyId = studyApplySaveDTO.getStudyId();

        if(sar.findByMemberEntity_IdAndStudyEntity_Id(memberId,studyId)==null){
            MemberEntity memberEntity = mr.findById(memberId).get();
            StudyEntity studyEntity = sr.findById(studyId).get();
            sar.save(StudyApplyEntity.toStudyApplySaveEntity(memberEntity,studyEntity));

            return "ok";
        }
        else {
            return "no";
        }

    }

    @Override
    public List<StudyApplyDetailDTO> findByStudyApplyId(Long memberId) {
        List<StudyApplyEntity> studyApplyEntityList = sar.findAllByMemberEntity_Id(memberId);
        List<StudyApplyDetailDTO> studyApplyList = new ArrayList<>();

        for(StudyApplyEntity s : studyApplyEntityList) {
            studyApplyList.add(StudyApplyDetailDTO.toStudyApplyDetailDTO(s));
        }


        return studyApplyList;
    }

    @Override
    public List<StudyApplyDetailDTO> myApplyList(Long memberId) {
        StudyApplyEntity studyApplyEntity= sar.findById(memberId).get();
        List<StudyApplyEntity> studyApplyEntityList = studyApplyEntity.getMemberEntity().getStudyApplyEntityList();
        List<StudyApplyDetailDTO> applyList = new ArrayList<>();

        for(StudyApplyEntity s : studyApplyEntityList) {
            applyList.add(StudyApplyDetailDTO.toStudyApplyDetailDTO(s));
        }


        return applyList;
    }

    @Override
    public void updateByApplyState(Long studyApplyId) {
        samr.update(studyApplyId);
    }

    @Override
    public void deleteById(Long studyApplyId) {
        sar.deleteById(studyApplyId);
    }

}

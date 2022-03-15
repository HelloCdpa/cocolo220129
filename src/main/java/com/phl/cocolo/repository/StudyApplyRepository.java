package com.phl.cocolo.repository;

import com.phl.cocolo.entity.StudyApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyApplyRepository extends JpaRepository<StudyApplyEntity,Long> {
    StudyApplyEntity findByMemberEntity_IdAndStudyEntity_Id(Long memberId,Long studyId);

    List<StudyApplyEntity> findAllByMemberEntity_Id(Long memberId);

    List<StudyApplyEntity> findAllByStudyEntity_MemberEntity_Id(Long memberId);
}

package com.phl.cocolo.repository;

import com.phl.cocolo.entity.StudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudyRepository extends JpaRepository<StudyEntity,Long> {

    @Modifying
    @Query(value = "update StudyEntity s set s.studyCount = s.studyCount+1 where s.id = :studyId")
    int studyCount(@Param("studyId") Long studyId);

    List<StudyEntity> findAllByMemberEntity_Id(Long memberId);
}

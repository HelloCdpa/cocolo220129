package com.phl.cocolo.repository;

import com.phl.cocolo.dto.MenteeDetailDTO;
import com.phl.cocolo.entity.MenteeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenteeRepository extends JpaRepository<MenteeEntity,Long> {
    List<MenteeEntity> findAllByMemberEntity_Id (Long memberId);
    List<MenteeEntity> findAllByMentoringEntity_MemberEntity_Id(Long memberId);

    @Modifying
    @Query(value = "update MenteeEntity m set m.menteeCount = m.menteeCount+1 where m.id = :menteeId")
    int updateMenteeCount(@Param("menteeId") Long menteeId);

}

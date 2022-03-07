package com.phl.cocolo.repository;

import com.phl.cocolo.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity,Long> {
    ReviewEntity findByMemberEntity_IdAndOnClassEntity_Id(Long memberId, Long onClassId);
}

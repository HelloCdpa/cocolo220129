package com.phl.cocolo.repository;

import com.phl.cocolo.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity,Long> {
    Optional<LikeEntity> findByBoardEntity_IdAndMemberEntity_Id(Long boardId, Long memberId);
    void  deleteByBoardEntity_IdAndMemberEntity_Id(Long boardId, Long memberId);
}

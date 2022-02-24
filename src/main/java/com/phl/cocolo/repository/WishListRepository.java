package com.phl.cocolo.repository;

import com.phl.cocolo.entity.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishListEntity,Long> {
    Optional<WishListEntity> findByMemberEntity_IdAndOnClassEntity_Id(Long memberId, Long onClassId);

    List<WishListEntity> findByMemberEntity_Id(Long memberId);

    @Transactional
    void deleteByMemberEntity_IdAndOnClassEntity_Id(Long memberId, Long onClassId);
}

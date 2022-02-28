package com.phl.cocolo.repository;

import com.phl.cocolo.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity,Long> {
    Optional<CartEntity> findByMemberEntity_IdAndOnClassEntity_Id(Long memberId, Long onClassId);

    List<CartEntity> findByMemberEntity_Id(Long memberId);

    void deleteAllByMemberEntity_Id(Long memberId);
}

package com.phl.cocolo.repository;

import com.phl.cocolo.entity.MyClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyClassRepository extends JpaRepository<MyClassEntity,Long> {
    // 회원아이디로 MyClass 에 있는 모든 정보 가져오기
    List<MyClassEntity> findAllByMemberEntity_Id(Long memberId);
    List<MyClassEntity> findByMemberEntity_IdAndOnClassEntity_Id(Long memberId,Long onClassId);
}

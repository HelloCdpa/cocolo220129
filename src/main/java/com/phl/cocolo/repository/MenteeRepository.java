package com.phl.cocolo.repository;

import com.phl.cocolo.dto.MenteeDetailDTO;
import com.phl.cocolo.entity.MenteeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenteeRepository extends JpaRepository<MenteeEntity,Long> {
    List<MenteeEntity> findAllByMemberEntity_Id (Long memberId);


}

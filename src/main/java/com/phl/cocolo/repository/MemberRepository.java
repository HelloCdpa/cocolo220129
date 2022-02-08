package com.phl.cocolo.repository;

import com.phl.cocolo.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    MemberEntity findByMemberEmail(String MemberEmail);

}

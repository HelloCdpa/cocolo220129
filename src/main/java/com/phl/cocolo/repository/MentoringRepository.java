package com.phl.cocolo.repository;

import com.phl.cocolo.entity.MentoringEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MentoringRepository extends JpaRepository<MentoringEntity,Long> {
}

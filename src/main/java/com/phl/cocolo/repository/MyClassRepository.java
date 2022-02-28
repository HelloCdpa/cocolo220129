package com.phl.cocolo.repository;

import com.phl.cocolo.entity.MyClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyClassRepository extends JpaRepository<MyClassEntity,Long> {
}

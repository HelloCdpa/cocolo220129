package com.phl.cocolo.repository;

import com.phl.cocolo.entity.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishListEntity,Long> {
}

package com.phl.cocolo.repository;

import com.phl.cocolo.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatMessageEntity,Long> {
}

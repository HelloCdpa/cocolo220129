package com.phl.cocolo.repository;

import com.phl.cocolo.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessageEntity,Long> {
    List<ChatMessageEntity> findAllByChatRoomEntity_RoomId(String roomId);

}

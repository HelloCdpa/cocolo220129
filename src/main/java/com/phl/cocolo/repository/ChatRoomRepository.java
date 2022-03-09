package com.phl.cocolo.repository;

import com.phl.cocolo.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity,Long> {
    ChatRoomEntity findByRoomId(String roomId);

}

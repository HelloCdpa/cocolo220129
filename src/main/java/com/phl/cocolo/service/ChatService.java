package com.phl.cocolo.service;

import com.phl.cocolo.dto.ChatRoomDTO;
import com.phl.cocolo.dto.ChatRoomDetailDTO;

import java.util.List;

public interface ChatService {
    List<ChatRoomDetailDTO> findAllRooms();

    ChatRoomDetailDTO findRoomById(String id);

    void createChatRoomDTO(String name);
}

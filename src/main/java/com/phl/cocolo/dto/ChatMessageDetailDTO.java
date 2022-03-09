package com.phl.cocolo.dto;

import com.phl.cocolo.entity.ChatMessageEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDetailDTO {
    private Long chatRoomId;
    private String roomId;
    private String writer;
    private String message;

    public static ChatMessageDetailDTO toChatMessageDetailDTO(ChatMessageEntity chatMessageEntity){
        ChatMessageDetailDTO chatMessageDetailDTO = new ChatMessageDetailDTO();

        chatMessageDetailDTO.setRoomId(chatMessageEntity.getChatRoomEntity().getRoomId());
        chatMessageDetailDTO.setChatRoomId(chatMessageEntity.getChatRoomEntity().getId());
        chatMessageDetailDTO.setWriter(chatMessageEntity.getWriter());
        chatMessageDetailDTO.setMessage(chatMessageEntity.getMessage());

        return chatMessageDetailDTO;

    }

}
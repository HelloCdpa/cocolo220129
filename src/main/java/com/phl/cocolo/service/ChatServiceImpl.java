package com.phl.cocolo.service;

import com.phl.cocolo.dto.ChatRoomDTO;
import com.phl.cocolo.dto.ChatRoomDetailDTO;
import com.phl.cocolo.entity.ChatMessageEntity;
import com.phl.cocolo.entity.ChatRoomEntity;
import com.phl.cocolo.repository.ChatRepository;
import com.phl.cocolo.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
    private final ChatRoomRepository crr;
    private final ChatRepository cr;
    
    
    private Map<String, ChatRoomDTO> chatRoomDTOMap;

    @PostConstruct
    private void init(){
        chatRoomDTOMap = new LinkedHashMap<>();
    }
    //채팅방 전체보기
    @Override
    public List<ChatRoomDetailDTO> findAllRooms(){
        List<ChatRoomEntity> chatRoomEntityList = crr.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<ChatRoomDetailDTO> chatRoomList = new ArrayList<>();

        for (ChatRoomEntity c : chatRoomEntityList){
            chatRoomList.add(ChatRoomDetailDTO.toChatRoomDetailDTO(c));
        }

        return chatRoomList;
    }
    // 채팅방 Id로 채팅방찾기
    @Override
    public ChatRoomDetailDTO findRoomById(String roomId){
        ChatRoomEntity chatRoomEntity = crr.findByRoomId(roomId);
        ChatRoomDetailDTO chatRoomDetailDTO = ChatRoomDetailDTO.toChatRoomDetailDTO(chatRoomEntity);
        return chatRoomDetailDTO;
    }

    //채팅방 생성하기
    @Override
    public void createChatRoomDTO(String name){

        ChatRoomDTO room = ChatRoomDTO.create(name);
        ChatRoomEntity chatRoomEntity = ChatRoomEntity.toChatRoomEntity(room.getName(),room.getRoomId());
        crr.save(chatRoomEntity);

    }
}

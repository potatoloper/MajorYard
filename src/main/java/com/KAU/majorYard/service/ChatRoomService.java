package com.KAU.majorYard.service;

import com.KAU.majorYard.entity.ChatRoom;
import com.KAU.majorYard.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public Long save(ChatRoom chatRoom) {
        chatRoomRepository.save(chatRoom);
        return chatRoom.getId();
    }

    public ChatRoom findById(Long id) {
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(id);
        return optionalChatRoom.get();
    }

    public List<ChatRoom> findAll() {
        return chatRoomRepository.findAllByOrderByIdDesc();
    }
}

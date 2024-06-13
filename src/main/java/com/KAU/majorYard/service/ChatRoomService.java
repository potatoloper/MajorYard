package com.KAU.majorYard.service;


import com.KAU.majorYard.dto.request.ChatRoomRequestDto;
import com.KAU.majorYard.entity.ChatRoom;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom save(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoom findById(Long id) {
        return chatRoomRepository.findById(id).orElse(null);
    }

    public List<ChatRoom> findAllByUser(User user) {
        return chatRoomRepository.findAllByUser(user);
    }

    public ChatRoom findByIdAndUser(Long id, User user) {
        return chatRoomRepository.findByIdAndUser(id, user).orElse(null);
    }

    public ChatRoom createChatRoom(ChatRoomRequestDto requestDto, User user) {
        ChatRoom chatRoom = ChatRoom.builder()
                .roomName(requestDto.getRoomName())
                .user(user)
                .build();
        return chatRoomRepository.save(chatRoom);
    }
}




    //    public Long save(ChatRoom chatRoom) {
//        chatRoomRepository.save(chatRoom);
//        return chatRoom.getId();
//    }
//}
package com.KAU.majorYard.service;

import com.KAU.majorYard.entity.ChatRoom;
import com.KAU.majorYard.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    private static final Logger logger = LoggerFactory.getLogger(ChatRoomService.class);


    public ChatRoom save(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }


    public ChatRoom findById(Long id) {
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(id);
        return optionalChatRoom.get();
    }


    public List<ChatRoom> findAll() {
        logger.info("Fetching all chat rooms from the database");
        List<ChatRoom> rooms = chatRoomRepository.findAllByOrderByIdDesc();
        logger.info("Number of chat rooms found: {}", rooms.size());
        return rooms;

    }

    //    public Long save(ChatRoom chatRoom) {
//        chatRoomRepository.save(chatRoom);
//        return chatRoom.getId();
//    }
}
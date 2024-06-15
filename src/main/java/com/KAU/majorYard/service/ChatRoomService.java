package com.KAU.majorYard.service;

import com.KAU.majorYard.dto.request.ChatRoomRequestDto;
import com.KAU.majorYard.dto.response.ChatMessageResponseDto;
import com.KAU.majorYard.entity.ChatMessage;
import com.KAU.majorYard.entity.ChatRoom;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.repository.ChatMessageRepository;
import com.KAU.majorYard.repository.ChatRoomRepository;
import com.KAU.majorYard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class  ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    public ChatRoom save(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    public Optional <ChatRoom> findById(Long id) {
        return chatRoomRepository.findById(id);
    }

    public List<ChatRoom> findAllByUserIdOrSenderId(Long user_id, Long sender_id) {
        return chatRoomRepository.findAllByUserIdOrSenderId(user_id, sender_id);
    }

    public Optional<ChatRoom> findByIdAndUser(Long id, User user) {
        return chatRoomRepository.findByIdAndUser(id, user);
    }
    public ChatRoom createChatRoom(ChatRoomRequestDto requestDto, User user) {
        ChatRoom chatRoom = ChatRoom.builder()
                .roomName(requestDto.getRoomName())
                .user(user)
                .build();
        return chatRoomRepository.save(chatRoom);
    }

    @Transactional
    public void deleteChatRoom(Long roomId, Long userId) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));

        if (!chatRoom.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not authorized to delete this chat room");
        }

        chatRoomRepository.deleteById(roomId);
    }

    @Transactional(readOnly = true)
    public List<ChatMessageResponseDto> getMessagesByChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));

        List<ChatMessage> messages = chatMessageRepository.findByChatRoom(chatRoom);

        return messages.stream()
                .map(chatMessage -> {
                    User sender = userRepository.findById(chatMessage.getSenderId())
                            .orElseThrow(() -> new RuntimeException("User not found with id: " + chatMessage.getSenderId()));
                    return ChatMessageResponseDto.from(chatMessage, sender.getUserName());
                })
                .collect(Collectors.toList());
    }
}

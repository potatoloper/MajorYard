package com.KAU.majorYard.service;

import com.KAU.majorYard.dto.request.ChatMessageRequestDto;
import com.KAU.majorYard.dto.response.ChatMessageResponseDto;
import com.KAU.majorYard.entity.ChatMessage;
import com.KAU.majorYard.entity.ChatRoom;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.repository.ChatMessageRepository;
import com.KAU.majorYard.repository.ChatRoomRepository;
import com.KAU.majorYard.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomService chatRoomService;
    private final UserRepository userRepository;

    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> findByChatRoomIdAndUserId(Long chatRoomId, Long userId) {
        return chatMessageRepository.findByChatRoomIdAndUserId(chatRoomId, userId);
    }

    @Transactional
    public ChatMessage createAndSaveMessage(Long roomId, ChatMessageRequestDto requestDto) {
        ChatRoom chatRoom = chatRoomService.findById(roomId);
        if (chatRoom == null) {
            throw new EntityNotFoundException("Chat room not found with id: " + roomId);
        }

        User sender = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid sender ID"));

        ChatMessage chatMessage = ChatMessage.builder()
                .senderId(requestDto.getUserId())
                .senderName(sender.getUserName())
                .text(requestDto.getText())
                .chatRoom(chatRoom)
                .build();

        return chatMessageRepository.save(chatMessage);
    }

    @Transactional(readOnly = true)
    public List<ChatMessageResponseDto> getMessages(Long roomId, Long userId) {
        List<ChatMessage> messages = chatMessageRepository.findByChatRoomIdAndUserId(roomId, userId);
        return messages.stream()
                .map(chatMessage -> {
                    User sender = userRepository.findById(chatMessage.getSenderId())
                            .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + chatMessage.getSenderId()));
                    return ChatMessageResponseDto.from(chatMessage, sender.getUserName());
                })
                .collect(Collectors.toList());
    }

    public ChatMessageResponseDto sendMessage(Long chatRoomId, ChatMessageRequestDto requestDto) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid chat room ID"));

        User sender = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid sender ID"));

        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(chatRoom)
                .senderId(requestDto.getUserId())
                .senderName(sender.getUserName())
                .text(requestDto.getText())
                .build();

        ChatMessage savedMessage = chatMessageRepository.save(chatMessage);

        return ChatMessageResponseDto.from(savedMessage, sender.getUserName());
    }
}

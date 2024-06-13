package com.KAU.majorYard.service;

import com.KAU.majorYard.dto.request.ChatMessageRequestDto;
import com.KAU.majorYard.dto.response.ChatMessageResponseDto;
import com.KAU.majorYard.entity.ChatMessage;
import com.KAU.majorYard.entity.ChatRoom;
import com.KAU.majorYard.repository.ChatMessageRepository;
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
    private final ChatRoomService chatRoomService;

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

        ChatMessage chatMessage = ChatMessage.builder()
                .senderName(requestDto.getSenderName())
                .text(requestDto.getText())
                .chatRoom(chatRoom)
                .build();

        return chatMessageRepository.save(chatMessage);
    }

    @Transactional(readOnly = true)
    public List<ChatMessageResponseDto> getMessages(Long roomId, Long userId) {
        List<ChatMessage> messages = chatMessageRepository.findByChatRoomIdAndUserId(roomId, userId);
        return messages.stream()
                .map(message -> ChatMessageResponseDto.builder()
                        .id(message.getId())
                        .senderName(message.getSenderName())
                        .text(message.getText())
                        .createdTime(message.getCreatedTime())
                        .userId(message.getUser().getId())
                        .build())
                .collect(Collectors.toList());
    }
}

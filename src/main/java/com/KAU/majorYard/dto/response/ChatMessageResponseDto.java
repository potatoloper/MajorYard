package com.KAU.majorYard.dto.response;

import com.KAU.majorYard.entity.ChatMessage;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ChatMessageResponseDto {
    private Long id;
    private Long senderId;
    private String senderName;
    private String text;
    private Timestamp createdTime;
    private Long chatRoomId;

    @Builder
    public ChatMessageResponseDto(Long id, Long senderId, String senderName, String text, Timestamp createdTime, Long chatRoomId) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
        this.text = text;
        this.createdTime = createdTime;
        this.chatRoomId = chatRoomId;
    }

    public static ChatMessageResponseDto from(ChatMessage chatMessage, String senderName) {
        return ChatMessageResponseDto.builder()
                .id(chatMessage.getId())
                .senderId(chatMessage.getSenderId())
                .senderName(senderName)
                .text(chatMessage.getText())
                .createdTime(chatMessage.getCreatedTime())
                .chatRoomId(chatMessage.getChatRoom().getId())
                .build();
    }
}

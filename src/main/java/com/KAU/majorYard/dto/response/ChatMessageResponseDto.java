package com.KAU.majorYard.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ChatMessageResponseDto {
    private final Long id;
    private final String senderName;
    private final String text;
    private final Timestamp createdTime;
    private final Long userId;

    @Builder
    public ChatMessageResponseDto(Long id, String senderName, String text, Timestamp createdTime, Long userId) {
        this.id = id;
        this.senderName = senderName;
        this.text = text;
        this.createdTime = createdTime;
        this.userId = userId;
    }
}

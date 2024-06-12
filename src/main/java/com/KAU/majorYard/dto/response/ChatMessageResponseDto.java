package com.KAU.majorYard.dto.response;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class ChatMessageResponseDto {
    private final Long id;
    private final String text;
    private final String senderName;
    private final String createdTime;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ChatMessageResponseDto(Long id, String senderName, String text, Timestamp createdTime) {
        this.id = id;
        this.senderName = senderName;
        this.text = text;
        this.createdTime = createdTime != null ? createdTime.toLocalDateTime().format(formatter) : "";
    }

    // 게터와 세터
    public Long getId() {
        return id;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getText() {
        return text;
    }

    public String getCreatedTime() {
        return createdTime;
    }
}

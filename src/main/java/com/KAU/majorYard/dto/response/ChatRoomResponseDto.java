package com.KAU.majorYard.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoomResponseDto {
    private Long id;
    private String roomName;
    private Long userId;

    private Long senderId;

    private String senderName;

    @Builder
    public ChatRoomResponseDto(Long id, String roomName, Long userId, Long senderId, String senderName ) {
        this.id = id;
        this.roomName = roomName;
        this.userId = userId;
        this.senderId = senderId;
        this.senderName = senderName;
    }
}

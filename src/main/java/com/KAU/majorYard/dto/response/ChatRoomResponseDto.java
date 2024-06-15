package com.KAU.majorYard.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ChatRoomResponseDto {
    private Long id;
    private String roomName;
    private Long userId;


    @Builder
    public ChatRoomResponseDto(Long id, String roomName, Long userId) {
        this.id = id;
        this.roomName = roomName;
        this.userId = userId;
    }
}

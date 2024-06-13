package com.KAU.majorYard.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChatRoomRequestDto {
    private Long id;
    private String roomName;

    private Long userId; //userId로 구분
}
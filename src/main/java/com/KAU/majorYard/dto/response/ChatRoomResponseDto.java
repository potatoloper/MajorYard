package com.KAU.majorYard.dto.response;

public class ChatRoomResponseDto {
    private Long id;
    private String roomName;

    // 생성자
    public ChatRoomResponseDto(Long id, String roomName) {
        this.id = id;
        this.roomName = roomName;
    }

    // 게터와 세터
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}

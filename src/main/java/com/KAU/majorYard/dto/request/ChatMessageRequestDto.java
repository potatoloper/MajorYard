package com.KAU.majorYard.dto.request;

public class ChatMessageRequestDto {
    private String senderName;
    private String text;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;  // 세터 추가
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;  // 세터 추가
    }
}

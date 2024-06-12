package com.KAU.majorYard.dto.response;

public class ChatMessageResponse {
    private String text;

    public ChatMessageResponse(String text) {
        this.text = text;
    }

    // Getter
    public String gettext() {
        return text;
    }
}

package com.KAU.majorYard.dto.request;

import com.KAU.majorYard.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter @Setter
@Builder
public class ChatMessageRequestDto {
    private Long roomId;

    private Long userId;

    private Timestamp createdTime;

    private String senderNickName;
    private String text;

}

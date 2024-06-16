package com.KAU.majorYard.dto.request;

import com.KAU.majorYard.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Builder
public class ChatMessageRequestDto {
    private Long roomId;

    private Long userId;
    private String text;

}

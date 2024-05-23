package com.KAU.majorYard.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpdateRequestDto {
    @NotNull
    private String commentContent;
    //private Long userNo;
}

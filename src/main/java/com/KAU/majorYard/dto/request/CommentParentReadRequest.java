package com.KAU.majorYard.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentParentReadRequest {
    @NotNull
    private String sort;
}

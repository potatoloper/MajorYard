package com.KAU.majorYard.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentPagingRequestDto {
    private int page;
    private int size;
    private String sort;
}

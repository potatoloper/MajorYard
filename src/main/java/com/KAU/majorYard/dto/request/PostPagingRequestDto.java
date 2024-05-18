package com.KAU.majorYard.dto.request;

import lombok.Data;

@Data
public class PostPagingRequestDto {

    private int page;
    private int size;
    private String sort;
}

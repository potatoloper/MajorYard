package com.KAU.majorYard.dto.request;

import com.KAU.majorYard.entity.Img;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequestDto {
    @NotNull
    private String postTitle;
    @NotNull
    private String postContent;
    private List<Img> postImgs;
}

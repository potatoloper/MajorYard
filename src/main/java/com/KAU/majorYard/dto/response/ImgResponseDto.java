package com.KAU.majorYard.dto.response;

import com.KAU.majorYard.entity.Img;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ImgResponseDto {

    private Long id;
    private String originalFileName;
    private String storedFileName;

    @Builder
    public ImgResponseDto(List<Img> imgs){
        for (Img img : imgs){
            this.id = img.getId();
            this.originalFileName = img.getOriginalFileName();
            this.storedFileName = img.getStoredFileName();
        }
    }

    @Builder
    public ImgResponseDto(Img img){
        this.id = img.getId();
        this.originalFileName = img.getOriginalFileName();
        this.storedFileName = img.getStoredFileName();
    }

}

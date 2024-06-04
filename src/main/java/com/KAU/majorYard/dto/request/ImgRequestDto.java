package com.KAU.majorYard.dto.request;

import com.KAU.majorYard.entity.Img;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImgRequestDto {
    private List<MultipartFile> multipartFiles;
    private MultipartFile multipartFile;
    private String originalFileName;
    private String savedPath; // 저장된 파일 경로

    @Builder
    public Img toEntity(String storedName){
        return Img.builder()
                .originalFileName(this.originalFileName)
                .storedFileName(storedName)
                .build();
    }

}

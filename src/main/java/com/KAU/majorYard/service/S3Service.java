package com.KAU.majorYard.service;


import com.KAU.majorYard.dto.request.ImgRequestDto;
import com.KAU.majorYard.dto.response.ImgResponseDto;
import com.KAU.majorYard.entity.Img;
import com.KAU.majorYard.entity.Post;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.repository.ImgRepository;
import com.KAU.majorYard.repository.PostRepository;
import com.KAU.majorYard.repository.UserRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;
    private final ImgRepository imgRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    // 확장자 추출
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    @Transactional
    public Img saveImage(MultipartFile multipartFile, Post post) throws IOException {

        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();

        // 원본 파일명을 서버에 저장된 파일명으로 변경하여 storedFileName에 저장하기 위함 (중복 비허용)
        // 파일명이 중복되지 않도록 UUID를 붙여 설정, 확장자 유지
        String storedFileName = UUID.randomUUID() + "." + extractExt(originalFileName);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        // S3에 파일 업로드
        amazonS3.putObject(bucket, storedFileName, multipartFile.getInputStream(), metadata);

        Img img = imgRepository.save(Img.builder()
                .originalFileName(originalFileName)
                .storedFileName(storedFileName)
                .build());

        img.changePost(post);

        return img;
    }

    public List<ResponseEntity<UrlResource>> downloadImg(Long postId) {
        // boardId에 해당하는 게시글이 없으면 null return
        Post post = postRepository.findById(postId).orElseThrow(()->new RuntimeException("게시글이 확인되지 않습니다."));

        if (post.getPostImgs() == null) {
            return null;
        }

        List<ResponseEntity<UrlResource>> entityList = new ArrayList<>();

        List<Img> imgs = post.getPostImgs();
        for (Img img : imgs) {
            UrlResource urlResource = new UrlResource(amazonS3.getUrl(bucket, img.getStoredFileName()));

            // 올려져 있는 파일명이 한글인 경우, 이 작업을 안해주면 한글이 깨질 수 있음
            String encodedUploadFileName = UriUtils.encode(img.getStoredFileName(), StandardCharsets.UTF_8);
            String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

            // header에 CONTENT_DISPOSITION 설정을 통해 클릭 시 다운로드 진행
            entityList.add(ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                    .body(urlResource));
        }
        return entityList;
    }

    // 해당 파일명에 해당하는 이미지의 S3 URL 주소 반환
    public String getFullPath(String filename) {
        return amazonS3.getUrl(bucket, filename).toString();
    }

    // 해당 파일명들에 해당하는 이미지의 S3 URL 주소 반환
    public List<String> getFullPath(List<Img> imgs) {
        List<String> filenames = new ArrayList<>();
        for (Img img : imgs){
            filenames.add(amazonS3.getUrl(bucket, img.getStoredFileName()).toString());
        }
        return filenames;
    }

    @Transactional
    public void deleteImage(Long imgId)  {
        Img img = imgRepository.findById(imgId).orElseThrow(() -> new RuntimeException("이미지를 찾을 수 없습니다"));
        imgRepository.deleteById(imgId);
        amazonS3.deleteObject(bucket, img.getStoredFileName());
    }

    // 프로필 이미지 저장
    @Transactional
    public String putProfImage(Long userNo, MultipartFile multipartFile) throws IOException {

        User user = userRepository.findById(userNo).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다"));

        // 아무 파일도 받지 못하면 null을 리턴
        if (multipartFile.isEmpty()) {
            return null;
        }

        // 이전에 저장된 프로필 사진이 있으면 삭제
        if (user.getUserProfImg() != null){
            deleteProfImage(userNo);
        }

        String originalFileName = multipartFile.getOriginalFilename();

        // 원본 파일명을 서버에 저장된 파일명으로 변경하여 storedFileName에 저장하기 위함 (중복 비허용)
        String storedFileName = "profImg_" + UUID.randomUUID() + "." + extractExt(originalFileName);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        // S3에 파일 업로드
        amazonS3.putObject(bucket, storedFileName, multipartFile.getInputStream(), metadata);
        user.updateProfImg(storedFileName);

        return getFullPath(storedFileName);
    }

    // 프로필 이미지의 S3 전체 주소 조회
    @Transactional(readOnly = true)
    public String getProfImage(Long userNo){
        User user = userRepository.findById(userNo).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다"));
        if (user.getUserProfImg() == null){
            return null;
        }
        return getFullPath(user.getUserProfImg());
    }

    // 프로필 이미지 삭제
    @Transactional
    public void deleteProfImage(Long userNo)  {
        User user = userRepository.findById(userNo).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다"));
        amazonS3.deleteObject(bucket, user.getUserProfImg());
        user.deleteProfImg();
    }

}


package com.KAU.majorYard.service;

import com.KAU.majorYard.dto.request.BoardRequestDto.*;
import com.KAU.majorYard.dto.request.PostPagingRequestDto;
import com.KAU.majorYard.dto.request.PostSaveRequestDto;
import com.KAU.majorYard.dto.request.PostUpdateRequestDto;
import com.KAU.majorYard.dto.response.PostPagingResponseDto;
import com.KAU.majorYard.dto.response.PostReadResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    void savePost(PostSaveRequestDto postDto, List<MultipartFile> multipartFiles) throws IOException;

    Page<PostPagingResponseDto> findAllPosts(PostPagingRequestDto postPagingRequestDto);

    PostReadResponseDto findPostById(Long id);

    void updatePosts(Long id, PostUpdateRequestDto postDto);

    void pmtUpdateAnsweredPost(Long id);

    void deletePosts(Long id);
}

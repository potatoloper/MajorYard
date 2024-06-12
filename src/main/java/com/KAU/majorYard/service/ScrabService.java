package com.KAU.majorYard.service;

import com.KAU.majorYard.dto.response.PostPagingResponseDto;
import com.KAU.majorYard.dto.response.ScrabResponseDto;
import com.KAU.majorYard.entity.Post;
import com.KAU.majorYard.entity.Scrab;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.exception.CustomErrorCode;
import com.KAU.majorYard.exception.CustomException;
import com.KAU.majorYard.repository.PostRepository;
import com.KAU.majorYard.repository.ScrabRepository;
import com.KAU.majorYard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrabService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ScrabRepository scrabRepository;
    private final S3Service s3Service;

    @Transactional
    public void PostScrabUp(Long userNo, Long postNo) throws IllegalAccessException {
        User user = userRepository.findById(userNo).orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        Post post = postRepository.findById(postNo).orElseThrow(()-> new CustomException(CustomErrorCode.POST_NOT_FOUND));

        // 이미 유저가 해당 게시글에 스크랩을 눌렀을 때
        if (scrabRepository.findByUserIdAndPostId(userNo, postNo) != null){
            throw new IllegalAccessException("이미 스크랩을 눌렀습니다!");
        }

        Scrab scrab = scrabRepository.save(Scrab.builder()
                .user(user)
                .post(post)
                .build());
        post.increaseScrabs();
    }

    @Transactional
    public void PostScrabDown(Long userNo, Long postNo){
        User user = userRepository.findById(userNo).orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        Post post = postRepository.findById(postNo).orElseThrow(()-> new CustomException(CustomErrorCode.POST_NOT_FOUND));

        Scrab scrab = scrabRepository.findByUserId(userNo);
        scrabRepository.delete(scrab);
        post.decreaseScarbs();
    }

    @Transactional(readOnly = true)
    public List<ScrabResponseDto> getScrabPosts(Long userNo){
        List<Scrab> scrabs = scrabRepository.findAllByUserId(userNo);
        List<Post> posts = postRepository.findAllByUserId(userNo);
        List<ScrabResponseDto> scarbList = new ArrayList<>();
        if (posts != null){
            for (Scrab scrab : scrabs){
                ;
                //scarbList.add(new ScrabResponseDto(post, s3Service.getFullPath(post.getPostImgs())));
                scarbList.add(new ScrabResponseDto(scrab.getPost(), s3Service.getFullPath(scrab.getPost().getPostImgs())));
            }
        }

        return scarbList;
    }
}

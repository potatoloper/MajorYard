package com.KAU.majorYard.service;

import com.KAU.majorYard.entity.Like;
import com.KAU.majorYard.entity.Post;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.exception.CustomErrorCode;
import com.KAU.majorYard.exception.CustomException;
import com.KAU.majorYard.repository.LikeRepository;
import com.KAU.majorYard.repository.PostRepository;
import com.KAU.majorYard.repository.ScrabRepository;
import com.KAU.majorYard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    // 좋아요 등록
    @Transactional
    public void PostLikeUp(Long userNo, Long postNo) throws IllegalAccessException {
        User user = userRepository.findById(userNo).orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        Post post = postRepository.findById(postNo).orElseThrow(()-> new CustomException(CustomErrorCode.POST_NOT_FOUND));

        // 이미 유저가 해당 게시글에 좋아요를 눌렀을 때
        if (likeRepository.findByUserIdAndPostId(userNo, postNo) != null){
            throw new IllegalAccessException("이미 좋아요를 눌렀습니다!");
        }

        Like like = likeRepository.save(Like.builder()
                .user(user)
                .post(post)
                .build());
        post.increaseLikes();
    }

    // 좋아요 취소
    @Transactional
    public void PostLikeDown(Long userNo, Long postNo){
        User user = userRepository.findById(userNo).orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        Post post = postRepository.findById(postNo).orElseThrow(()-> new CustomException(CustomErrorCode.POST_NOT_FOUND));

        Like like = likeRepository.findByUserIdAndPostId(userNo, postNo);
        likeRepository.delete(like);
        post.decreaseLikes();
    }
}

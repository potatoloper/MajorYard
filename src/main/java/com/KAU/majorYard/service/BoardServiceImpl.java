package com.KAU.majorYard.service;

import com.KAU.majorYard.dto.request.BoardRequestDto;
import com.KAU.majorYard.dto.request.BoardRequestDto.*;
import com.KAU.majorYard.entity.Board;
import com.KAU.majorYard.entity.Post;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.exception.CustomErrorCode;
import com.KAU.majorYard.exception.CustomException;
import com.KAU.majorYard.repository.BoardRepository;
import com.KAU.majorYard.repository.PostRepository;
import com.KAU.majorYard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void saveboard(BoardRequestDto boardDto) {
        // Method implementation (if needed)
    }

    @Transactional
    public void savePromotionPost(promotionBoard postDto) {
        User user = userRepository.findById(postDto.getUserNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        Board board = boardRepository.findById(postDto.getBoardNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.BOARD_NOT_FOUND));

        Post post = createPostByBoardType(postDto, user, board);
        postRepository.save(post);
    }

    @Transactional
    public void saveQuestionPost(questionBoard postDto) {
        User user = userRepository.findById(postDto.getUserNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        Board board = boardRepository.findById(postDto.getBoardNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.BOARD_NOT_FOUND));

        Post post = createPostByBoardType(postDto, user, board);
        postRepository.save(post);
    }

    @Transactional
    public void saveStudyPost(studyBoard postDto) {
        User user = userRepository.findById(postDto.getUserNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        Board board = boardRepository.findById(postDto.getBoardNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.BOARD_NOT_FOUND));

        Post post = createPostByBoardType(postDto, user, board);
        postRepository.save(post);
    }

    @Transactional
    public void saveFreePost(freeBoard postDto) {
        User user = userRepository.findById(postDto.getUserNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        Board board = boardRepository.findById(postDto.getBoardNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.BOARD_NOT_FOUND));

        Post post = createPostByBoardType(postDto, user, board);
        postRepository.save(post);
    }

    @Transactional
    public void saveIssuePost(issueBoard postDto) {
        User user = userRepository.findById(postDto.getUserNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        Board board = boardRepository.findById(postDto.getBoardNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.BOARD_NOT_FOUND));

        Post post = createPostByBoardType(postDto, user, board);
        postRepository.save(post);
    }

    private Post createPostByBoardType(promotionBoard postDto, User user, Board board) {
        return Post.builder()
                .postTitle(postDto.getPostTitle())
                .postContent(postDto.getPostContent())
                .postType(postDto.getPostType())
                .user(user)
                .board(board)
                .build();
    }

    private Post createPostByBoardType(questionBoard postDto, User user, Board board) {
        return Post.builder()
                .postTitle(postDto.getPostTitle())
                .postContent(postDto.getPostContent())
                .answered(postDto.getAnswered())
                .user(user)
                .board(board)
                .build();
    }

    private Post createPostByBoardType(studyBoard postDto, User user, Board board) {
        return Post.builder()
                .postTitle(postDto.getPostTitle())
                .postContent(postDto.getPostContent())
                .studyRegion(postDto.getStudyRegion())
                .studyPartyOf(postDto.getStudyPartyOf())
                .studyFee(postDto.getStudyFee())
                .studyUntil(postDto.getStudyUntil())
                .user(user)
                .board(board)
                .build();
    }

    private Post createPostByBoardType(freeBoard postDto, User user, Board board) {
        return Post.builder()
                .postTitle(postDto.getPostTitle())
                .postContent(postDto.getPostContent())
                .user(user)
                .board(board)
                .build();
    }

    private Post createPostByBoardType(issueBoard postDto, User user, Board board) {
        return Post.builder()
                .postTitle(postDto.getPostTitle())
                .postContent(postDto.getPostContent())
                .user(user)
                .board(board)
                .build();
    }
}

package com.KAU.majorYard.service;

import com.KAU.majorYard.dto.request.BoardRequestDto;

import com.KAU.majorYard.entity.Board;
import com.KAU.majorYard.entity.Post;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.entity.majorYard_enum.Answered;
import com.KAU.majorYard.entity.majorYard_enum.BoardName;
import com.KAU.majorYard.exception.CustomErrorCode;
import com.KAU.majorYard.exception.CustomException;
import com.KAU.majorYard.repository.BoardRepository;
import com.KAU.majorYard.repository.PostRepository;
import com.KAU.majorYard.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;

    @Transactional
    public void savePromotionPost(BoardRequestDto.promotionBoard postDto, List<MultipartFile> multipartFiles) throws IOException {
        User user = userRepository.findById(postDto.getUserNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        Board board = boardRepository.findById(postDto.getBoardNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.BOARD_NOT_FOUND));

        if (board.getBoardName() != BoardName.PromotionBoard) {
            throw new CustomException(CustomErrorCode.INVALID_BOARD_TYPE);
        }

        Post post = createPostByBoardType(postDto, user, board);
        postRepository.save(post);

        if(multipartFiles != null){
            for (MultipartFile multipartFile : multipartFiles){
                s3Service.saveImage(multipartFile, post);
            }
        }
    }

    @Transactional
    public void saveQuestionPost(BoardRequestDto.questionBoard postDto, List<MultipartFile> multipartFiles) throws IOException {
        User user = userRepository.findById(postDto.getUserNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        Board board = boardRepository.findById(postDto.getBoardNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.BOARD_NOT_FOUND));

        if (board.getBoardName() != BoardName.QuestionBoard) {
            throw new CustomException(CustomErrorCode.INVALID_BOARD_TYPE);
        }

        Post post = createPostByBoardType(postDto, user, board);
        postRepository.save(post);

        if(multipartFiles != null){
            for (MultipartFile multipartFile : multipartFiles){
                s3Service.saveImage(multipartFile, post);
            }
        }
    }

    @Transactional
    public void saveStudyPost(BoardRequestDto.studyBoard postDto, List<MultipartFile> multipartFiles) throws IOException {
        User user = userRepository.findById(postDto.getUserNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        Board board = boardRepository.findById(postDto.getBoardNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.BOARD_NOT_FOUND));

        if (board.getBoardName() != BoardName.StudyBoard) {
            throw new CustomException(CustomErrorCode.INVALID_BOARD_TYPE);
        }

        Post post = createPostByBoardType(postDto, user, board);
        postRepository.save(post);

        if(multipartFiles != null){
            for (MultipartFile multipartFile : multipartFiles){
                s3Service.saveImage(multipartFile, post);
            }
        }
    }

    @Transactional
    public void saveFreePost(BoardRequestDto.freeBoard postDto, List<MultipartFile> multipartFiles) throws IOException {
        User user = userRepository.findById(postDto.getUserNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        Board board = boardRepository.findById(postDto.getBoardNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.BOARD_NOT_FOUND));

        if (board.getBoardName() != BoardName.FreeBoard) {
            throw new CustomException(CustomErrorCode.INVALID_BOARD_TYPE);
        }

        Post post = createPostByBoardType(postDto, user, board);
        postRepository.save(post);

        if(multipartFiles != null){
            for (MultipartFile multipartFile : multipartFiles){
                s3Service.saveImage(multipartFile, post);
            }
        }
    }

    @Transactional
    public void saveIssuePost(BoardRequestDto.issueBoard postDto, List<MultipartFile> multipartFiles) throws IOException {
        User user = userRepository.findById(postDto.getUserNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        Board board = boardRepository.findById(postDto.getBoardNo())
                .orElseThrow(() -> new CustomException(CustomErrorCode.BOARD_NOT_FOUND));

        if (board.getBoardName() != BoardName.IssueBoard) {
            throw new CustomException(CustomErrorCode.INVALID_BOARD_TYPE);
        }

        Post post = createPostByBoardType(postDto, user, board);
        postRepository.save(post);

        if(multipartFiles != null){
            for (MultipartFile multipartFile : multipartFiles){
                s3Service.saveImage(multipartFile, post);
            }
        }
    }

    private Post createPostByBoardType(BoardRequestDto.promotionBoard postDto, User user, Board board) {
        return Post.builder()
                .postTitle(postDto.getPostTitle())
                .postContent(postDto.getPostContent())
                .postType(postDto.getPostType())
                .user(user)
                .board(board)
                .build();
    }

    private Post createPostByBoardType(BoardRequestDto.questionBoard postDto, User user, Board board) {
        return Post.builder()
                .postTitle(postDto.getPostTitle())
                .postContent(postDto.getPostContent())
                .answered(Answered.N)
                .user(user)
                .board(board)
                .build();
    }

    private Post createPostByBoardType(BoardRequestDto.studyBoard postDto, User user, Board board) {
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

    private Post createPostByBoardType(BoardRequestDto.freeBoard postDto, User user, Board board) {
        return Post.builder()
                .postTitle(postDto.getPostTitle())
                .postContent(postDto.getPostContent())
                .user(user)
                .board(board)
                .build();
    }

    private Post createPostByBoardType(BoardRequestDto.issueBoard postDto, User user, Board board) {
        return Post.builder()
                .postTitle(postDto.getPostTitle())
                .postContent(postDto.getPostContent())
                .user(user)
                .board(board)
                .url(postDto.getUrl())
                .build();
    }

}

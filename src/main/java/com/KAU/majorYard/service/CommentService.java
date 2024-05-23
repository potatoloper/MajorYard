package com.KAU.majorYard.service;

import com.KAU.majorYard.dto.request.*;
import com.KAU.majorYard.dto.response.CommentChildReadResponse;
import com.KAU.majorYard.dto.response.CommentPagingResponseDto;
import com.KAU.majorYard.dto.response.CommentParentReadResponse;
import com.KAU.majorYard.dto.response.CommentReadResponseDto;
import com.KAU.majorYard.entity.Comment;
import com.KAU.majorYard.entity.Post;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.repository.CommentRepository;
import com.KAU.majorYard.repository.PostRepository;
import com.KAU.majorYard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    //댓글 저장
    @Transactional
    public void saveComment(Long postNo, CommentSaveRequestDto commentDto){
        Post post = postRepository.findById(postNo).orElseThrow();
        User user = userRepository.findById(commentDto.getUserNo()).orElseThrow();
        Comment comment = commentRepository.save(commentDto.toEntity(user, post));
        post.increaseComments();
    }

    //대댓글 저장
    @Transactional
    public void saveChildComment(Long postNo, Long parentNo, CommentSaveChildRequestDto commentDto){
        Comment parent = commentRepository.findById(parentNo).orElseThrow(() -> new RuntimeException("부모 댓글이 확인되지 않습니다."));
        Post post = postRepository.findById(postNo).orElseThrow();
        User user = userRepository.findById(commentDto.getUserNo()).orElseThrow();
        Comment comment = commentRepository.save(commentDto.toEntity(user, post, parent));
        comment.updateDepth();
        post.increaseComments();
    }

    //댓글 페이징
    @Transactional(readOnly = true)
    public Page<CommentPagingResponseDto> findAllComments(Long postNo, CommentPagingRequestDto requestDto){
        Post post = postRepository.findById(postNo).orElseThrow(() -> new RuntimeException("게시글이 확인되지 않습니다."));

        Sort sort = Sort.by(Sort.Direction.fromString(requestDto.getSort()), "id");
        Pageable pageable = PageRequest.of(requestDto.getPage()-1, requestDto.getSize(), sort);
        Page<Comment> commentPages = commentRepository.findAll(pageable);

        Page<CommentPagingResponseDto> commentDTOPages = commentPages.map(commentPage -> new CommentPagingResponseDto(commentPage));
        return commentDTOPages;
    }

    //댓글 하나 조회
    @Transactional(readOnly = true)
    public CommentReadResponseDto findCommentById(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("댓글이 확인되지 않습니다."));
        return new CommentReadResponseDto(comment);
    }

    //자식 댓글들 조회
    @Transactional(readOnly = true)
    public List<CommentChildReadResponse> findChildComments(Long parentNo/*, CommentChildReadRequest requestDto*/){
        Comment comment = commentRepository.findById(parentNo).orElseThrow(() -> new RuntimeException("댓글이 확인되지 않습니다."));
        //Sort sort = Sort.by(Sort.Direction.fromString(requestDto.getSort()), "id");
        List<Comment> childs = comment.getChildComments();
        List<CommentChildReadResponse> responseList = new ArrayList<>();

        if (childs != null){
            for (Comment c : childs){
                responseList.add(new CommentChildReadResponse(c));
            }
        }
        return responseList;
    }

    //부모 댓글들 조회 (자식 댓글 조회 안 함)
    @Transactional(readOnly = true)
    public List<CommentParentReadResponse> findParentComments(Long postNo/*, CommentParentReadRequest requestDto*/){
        Post post = postRepository.findById(postNo).orElseThrow(() -> new RuntimeException("게시글이 확인되지 않습니다."));
        //Sort sort = Sort.by(Sort.Direction.fromString(requestDto.getSort()), "id");
        List<Comment> comments = post.getPostComments();
        List<CommentParentReadResponse> responseList = new ArrayList<>();

        if (comments != null){
            for (Comment c : comments){
                if(c.getParentComment() == null){
                    responseList.add(new CommentParentReadResponse(c));
                }
            }
        }

        return responseList;
    }

    //댓글 내용 수정
    @Transactional
    public void updateComments(Long id, CommentUpdateRequestDto requestDto){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("댓글이 확인되지 않습니다."));
        comment.update(requestDto.getCommentContent());
    }

    //댓글 삭제
    @Transactional
    public void deleteComment(Long postNo, Long id){
        commentRepository.findById(id).orElseThrow(() -> new RuntimeException("댓글이 확인되지 않습니다."));
        Post post = postRepository.findById(postNo).orElseThrow(() -> new RuntimeException("게시글이 확인되지 않습니다."));
        commentRepository.deleteById(id);
        post.decreaseComments();
    }
}

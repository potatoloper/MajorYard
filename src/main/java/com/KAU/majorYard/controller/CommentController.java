package com.KAU.majorYard.controller;

import com.KAU.majorYard.dto.CommonResponse;
import com.KAU.majorYard.dto.CommonRestResult;
import com.KAU.majorYard.dto.request.*;
import com.KAU.majorYard.dto.response.CommentChildReadResponse;
import com.KAU.majorYard.dto.response.CommentParentReadResponse;
import com.KAU.majorYard.dto.response.CommentReadResponseDto;
import com.KAU.majorYard.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 저장
    @PostMapping("/posts/{postNo}/comments/save")
    public CommonResponse saveComment(@PathVariable Long postNo, @RequestBody @Valid CommentSaveRequestDto request){
        String resultMsg;
        String resultCode;

        try {
            commentService.saveComment(postNo, request);
            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getMessage();
            return new CommonResponse(resultCode, resultMsg);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    //대댓글 저장
    @PostMapping("/posts/{postNo}/comments/{parentNo}/save")
    public CommonResponse saveComment(@PathVariable Long postNo, @PathVariable Long parentNo, @RequestBody @Valid CommentSaveChildRequestDto request){
        String resultMsg;
        String resultCode;

        try {
            commentService.saveChildComment(postNo, parentNo, request);
            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getMessage();
            return new CommonResponse(resultCode, resultMsg);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    //댓글 페이징 조회
//    @GetMapping("/posts/{postNo}/comments")
//    public CommonResponse getAllComment(@PathVariable Long postNo, @RequestBody @Valid CommentPagingRequestDto request){
//        String resultMsg;
//        String resultCode;
//
//        try {
//            Page<CommentPagingResponseDto> page = commentService.findAllComments(postNo, request);
//            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
//            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
//            return new CommonResponse(resultCode, resultMsg, page);
//
//        }catch (Exception e){
//            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
//            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
//            return new CommonResponse(resultCode, resultMsg);
//        }
//    }

    //부모 댓글들만 모두 조회
    @GetMapping("/posts/{postNo}/comments")
    public CommonResponse getParentComments(@PathVariable Long postNo/*, @RequestBody @Valid CommentParentReadRequest request*/){
        String resultMsg;
        String resultCode;

        try {
            List<CommentParentReadResponse> comments = commentService.findParentComments(postNo/*, request*/);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, comments);

        }catch (Exception e){
            System.out.println(e);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    // 특정 댓글의 대댓글(=자식 댓글) 모두 조회
    @GetMapping("/posts/{postNo}/comments/{commentNo}")
    public CommonResponse getChildComments(@PathVariable Long commentNo/*, @RequestBody @Valid CommentChildReadRequest request*/){
        String resultMsg;
        String resultCode;

        try {
            List<CommentChildReadResponse> comments = commentService.findChildComments(commentNo/*, request*/);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, comments);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    //댓글 한 개 조회
    @GetMapping("/posts/{postNo}/comments/{commentNo}/detail")
    public CommonResponse getComment(@PathVariable Long commentNo){
        String resultMsg;
        String resultCode;

        try {
            CommentReadResponseDto comment = commentService.findCommentById(commentNo);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, comment);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    //댓글 내용 수정
    //댓글 PK로 댓글을 식별해 수정하므로, 댓글과 대댓글 모두 포함된 수정 API이다.
    @PutMapping("/posts/{postNo}/comments/{commentNo}")
    //@ResponseStatus(HttpStatus.OK)
    public CommonResponse updatePost(@PathVariable Long commentNo, @RequestBody @Valid CommentUpdateRequestDto request){
        String resultMsg;
        String resultCode;

        try {
            commentService.updateComments(commentNo, request);
            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getMessage();
            return new CommonResponse(resultCode, resultMsg);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    //댓글 삭제
    //댓글 PK로 댓글을 식별해 삭제하므로, 댓글과 대댓글 모두 포함된 삭제 API다.
    @DeleteMapping("/posts/{postNo}/comments/{commentNo}")
    public CommonResponse deleteComment(@PathVariable Long postNo, @PathVariable Long commentNo){
        String resultMsg;
        String resultCode;

        try {
            commentService.deleteComment(postNo, commentNo);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }
}

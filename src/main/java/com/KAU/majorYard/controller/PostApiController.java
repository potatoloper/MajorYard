package com.KAU.majorYard.controller;

import com.KAU.majorYard.dto.CommonResponse;
import com.KAU.majorYard.dto.CommonRestResult;
import com.KAU.majorYard.dto.request.*;
import com.KAU.majorYard.dto.response.PostPagingResponseDto;
import com.KAU.majorYard.dto.response.PostReadResponseDto;
import com.KAU.majorYard.service.PostServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@RequestMapping(path = "/")
public class PostApiController {

    private final PostServiceImpl postService;

    @PostMapping("/posts/save")
    //@ResponseStatus(HttpStatus.CREATED)
    public CommonResponse savePost(@RequestBody @Valid PostSaveRequestDto request){

        String resultMsg;
        String resultCode;

        try {
            postService.savePost(request);
            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getMessage();
            return new CommonResponse(resultCode, resultMsg);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }

    }

    // 게시판에서 게시글 목록 조회
    @GetMapping("/posts/list")
    //@ResponseStatus(HttpStatus.OK)
    public CommonResponse getPostByPaging(@RequestBody @Valid PostPagingRequestDto request /*@Login User user*/){

        String resultMsg;
        String resultCode;

        try {
            Page<PostPagingResponseDto> page = postService.findAllPosts(request);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, page);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }

    }

    // 게시글 상세페이지 조회
    @GetMapping("/posts/list/{id}")
    public CommonResponse getPostById(@PathVariable Long id){
        String resultMsg;
        String resultCode;

        try {
            PostReadResponseDto post = postService.findPostById(id);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, post);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    // 게시글 업데이트
    @PutMapping("/posts/list/{id}")
    //@ResponseStatus(HttpStatus.OK)
    public CommonResponse updatePost(@PathVariable Long id, @RequestBody @Valid PostUpdateRequestDto request){
        String resultMsg;
        String resultCode;

        try {
            postService.updatePosts(id, request);
            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getMessage();
            return new CommonResponse(resultCode, resultMsg);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    // 게시글 삭제
    @DeleteMapping("/posts/list/{id}")
    public CommonResponse deletePost(@PathVariable Long id){
        String resultMsg;
        String resultCode;

        try {
            postService.deletePosts(id);
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

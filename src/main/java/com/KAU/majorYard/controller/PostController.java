package com.KAU.majorYard.controller;

import com.KAU.majorYard.dto.CommonResponse;
import com.KAU.majorYard.dto.CommonRestResult;
import com.KAU.majorYard.dto.request.*;
import com.KAU.majorYard.dto.response.PostPagingResponseDto;
import com.KAU.majorYard.dto.response.PostReadResponseDto;
import com.KAU.majorYard.service.BoardService;
import com.KAU.majorYard.service.LikeService;
import com.KAU.majorYard.service.PostServiceImpl;
import com.KAU.majorYard.service.S3Service;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/posts")
public class PostController {

    private final PostServiceImpl postService;
    private final BoardService boardService;
    private final LikeService likeService;
    private final S3Service s3Service;

    /* 게시글 저장 */

    @PostMapping("free/save")
    public CommonResponse saveFreePost(@RequestPart(value="posting") @Valid BoardRequestDto.freeBoard request,
                                       @RequestPart(value= "imgList", required = false) List<MultipartFile> imgList) throws IOException {
        boardService.saveFreePost(request, imgList);
        String resultCode = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getCode();
        String resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getMessage();
        return new CommonResponse(resultCode, resultMsg);
    }

    @PostMapping("promotion/save")
    public CommonResponse savePromotionPost(@RequestPart(value="posting") @Valid BoardRequestDto.promotionBoard request,
                                            @RequestPart(value= "imgList", required = false) List<MultipartFile> imgList) throws IOException {
        boardService.savePromotionPost(request, imgList);
        String resultCode = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getCode();
        String resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getMessage();
        return new CommonResponse(resultCode, resultMsg);
    }

    @PostMapping("question/save")
    public CommonResponse saveQuestionPost(@RequestPart(value="posting") @Valid BoardRequestDto.questionBoard request,
                                           @RequestPart(value= "imgList", required = false) List<MultipartFile> imgList) throws IOException {
        boardService.saveQuestionPost(request, imgList);
        String resultCode = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getCode();
        String resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getMessage();
        return new CommonResponse(resultCode, resultMsg);
    }

    @PostMapping("issue/save")
    public CommonResponse saveIssuePost(@RequestPart(value="posting") @Valid BoardRequestDto.issueBoard request,
                                        @RequestPart(value= "imgList", required = false) List<MultipartFile> imgList) throws IOException {
        boardService.saveIssuePost(request, imgList);
        String resultCode = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getCode();
        String resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getMessage();
        return new CommonResponse(resultCode, resultMsg);
    }

    @PostMapping("study/save")
    public CommonResponse saveStudyPost(@RequestPart(value="posting") @Valid BoardRequestDto.studyBoard request,
                                        @RequestPart(value= "imgList", required = false) List<MultipartFile> imgList) throws IOException {
        boardService.saveStudyPost(request, imgList);
        String resultCode = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getCode();
        String resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getMessage();
        return new CommonResponse(resultCode, resultMsg);
    }


    /* 게시판PK별 게시글 목록 조회 */

    @GetMapping("free/list/{boardNo}")
    public CommonResponse getFreePostByPaging(@PathVariable Long boardNo, @RequestParam(value = "page") int pageNum, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "DESC") String sort){

        String resultMsg;
        String resultCode;

        try {
            Page<PostPagingResponseDto> page = postService.findAllPostsByBoardPK(boardNo, pageNum, size, sort);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, page);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }

    }

    @GetMapping("promotion/list/{boardNo}")
    public CommonResponse getPromotionPostByPaging(@PathVariable Long boardNo, @RequestParam(value = "page") int pageNum, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "DESC") String sort){

        String resultMsg;
        String resultCode;

        try {
            Page<PostPagingResponseDto> page = postService.findAllPostsByBoardPK(boardNo, pageNum, size, sort);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, page);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }

    }

    @GetMapping("question/list/{boardNo}")
    public CommonResponse getQuestionPostByPaging(@PathVariable Long boardNo, @RequestParam(value = "page") int pageNum, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "DESC") String sort){

        String resultMsg;
        String resultCode;

        try {
            Page<PostPagingResponseDto> page = postService.findAllPostsByBoardPK(boardNo, pageNum, size, sort);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, page);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }

    }

    @GetMapping("issue/list/{boardNo}")
    public CommonResponse getIssuePostByPaging(@PathVariable Long boardNo, @RequestParam(value = "page") int pageNum, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "DESC") String sort){

        String resultMsg;
        String resultCode;

        try {
            Page<PostPagingResponseDto> page = postService.findAllPostsByBoardPK(boardNo, pageNum, size, sort);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, page);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }

    }


    @GetMapping("study/list/{boardNo}")
    public CommonResponse getStudyPostByPaging(@PathVariable Long boardNo, @RequestParam(value = "page") int pageNum, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "DESC") String sort){

        String resultMsg;
        String resultCode;

        try {
            Page<PostPagingResponseDto> page = postService.findAllPostsByBoardPK(boardNo, pageNum, size, sort);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, page);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }

    }


    /* 게시글 상세조회 */


    @GetMapping("free/list/{postNo}/detail")
    public CommonResponse getFreePostById(@PathVariable Long postNo){
        String resultMsg;
        String resultCode;

        try {
            PostReadResponseDto post = postService.findPostById(postNo);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, post);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    @GetMapping("promotion/list/{postNo}/detail")
    public CommonResponse getPromotionPostById(@PathVariable Long postNo){
        String resultMsg;
        String resultCode;

        try {
            PostReadResponseDto post = postService.findPostById(postNo);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, post);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    @GetMapping("question/list/{postNo}/detail")
    public CommonResponse getQuestionPostById(@PathVariable Long postNo){
        String resultMsg;
        String resultCode;

        try {
            PostReadResponseDto post = postService.findPostById(postNo);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, post);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    @GetMapping("issue/list/{postNo}/detail")
    public CommonResponse getIssuePostById(@PathVariable Long postNo){
        String resultMsg;
        String resultCode;

        try {
            PostReadResponseDto post = postService.findPostById(postNo);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, post);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    @GetMapping("study/list/{postNo}/detail")
    public CommonResponse getStudyPostById(@PathVariable Long postNo){
        String resultMsg;
        String resultCode;

        try {
            PostReadResponseDto post = postService.findPostById(postNo);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, post);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    // 게시글 좋아요
    @PostMapping("list/{postNo}/detail/{userNo}/like")
    public CommonResponse setLikeOnPost(@PathVariable Long userNo, @PathVariable Long postNo){
        String resultMsg;
        String resultCode;

        try {
            likeService.PostLikeUp(userNo, postNo);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }


    // 게시글 좋아요 해제
    @DeleteMapping("list/{postNo}/detail/{userNo}/{likeNo}")
    public CommonResponse deleteLikeOnPost(@PathVariable Long userNo, @PathVariable Long postNo, @PathVariable Long likeNo){
        String resultMsg;
        String resultCode;

        try {
            likeService.PostLikeDown(userNo, postNo, likeNo);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    /* 질문게시판 */

    // 질문게시글 답변상태 Y로 업데이트.
    // 질문게시글이 아닌 다른 게시판의 글에 적용시키면 에러 발생.
    @PutMapping("question/list/{postNo}")
    public CommonResponse updateAnsweredPost(@PathVariable Long postNo){
        String resultMsg;
        String resultCode;

        try {
            postService.pmtUpdateAnsweredPost(postNo);
            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getMessage();
            return new CommonResponse(resultCode, resultMsg);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }


    // * 작업용 * //


    //게시글 저장
    @PostMapping("/save")
    //@ResponseStatus(HttpStatus.CREATED)
    public CommonResponse savePost(@RequestPart(value="posting") @Valid PostSaveRequestDto request,
                                   @RequestPart(value= "imgList", required = false) List<MultipartFile> imgList){

        String resultMsg;
        String resultCode;

        try {
            //postService.savePost(request);
            postService.savePost(request, imgList); //이미지의 S3연동용
            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getMessage();
            return new CommonResponse(resultCode, resultMsg);

        }catch (Exception e){
            System.out.println(e);
            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }


    // 게시판에서 게시글 목록 조회
    @GetMapping("/list")
    //@ResponseStatus(HttpStatus.OK)
    public CommonResponse getPostByPaging(@RequestParam(value = "page") int pageNum, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "DESC") String sort){

        String resultMsg;
        String resultCode;

        try {
            Page<PostPagingResponseDto> page = postService.findAllPosts(pageNum, size, sort);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, page);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }

    }

    // 게시판PK별 게시글 목록 조회
    @GetMapping("{boardNo}/list")
    public CommonResponse getPostByPaging(@PathVariable Long boardNo, @RequestParam(value = "page") int pageNum, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "DESC") String sort){

        String resultMsg;
        String resultCode;

        try {
            Page<PostPagingResponseDto> page = postService.findAllPostsByBoardPK(boardNo, pageNum, size, sort);
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
    @GetMapping("/list/{id}")
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

    // 게시글 업데이트 (제목, 내용만)
    @PutMapping("/list/{id}")
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

    // 게시글 업데이트
//    @PutMapping("/list/{id}")
//    //@ResponseStatus(HttpStatus.OK)
//    public CommonResponse updatePost(@PathVariable Long id,
//                                     @RequestPart(value="posting") @Valid PostUpdateRequestDto request,
//                                     @RequestPart(value= "imgList", required = false) List<MultipartFile> imgList){
//        String resultMsg;
//        String resultCode;
//
//        try {
//            postService.updatePosts(id, request);
//            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getCode();
//            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getMessage();
//            return new CommonResponse(resultCode, resultMsg);
//
//        }catch (Exception e){
//            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getCode();
//            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getMessage();
//            return new CommonResponse(resultCode, resultMsg);
//        }
//    }



    // 게시글 삭제
    @DeleteMapping("/list/{id}")
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

//    @ResponseBody
//    @GetMapping("/list/{postId}/images/{fileName}")
//    public Resource showImage(@PathVariable Long postId, @PathVariable Long imgId, @PathVariable String fileName) throws MalformedURLException {
//        return new UrlResource(s3Service.getFullPath(fileName));
//    }

//    // 이미지 전체 다운로드
//    @GetMapping("/list/{postId}/images/download")
//    public List<ResponseEntity<UrlResource>> downloadImage(@PathVariable Long postId){
//        return s3Service.downloadImg(postId);
//    }
}

package com.KAU.majorYard.controller;

import com.KAU.majorYard.dto.CommonResponse;
import com.KAU.majorYard.dto.CommonRestResult;
import com.KAU.majorYard.dto.request.UserProfileUpdateRequest;
import com.KAU.majorYard.dto.response.PostPagingResponseDto;
import com.KAU.majorYard.dto.response.ScrabResponseDto;
import com.KAU.majorYard.entity.Img;
import com.KAU.majorYard.service.PostServiceImpl;
import com.KAU.majorYard.service.S3Service;
import com.KAU.majorYard.service.ScrabService;
import com.KAU.majorYard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final ScrabService scrabService;
    private final PostServiceImpl postService;
    private final S3Service s3Service;
    private final UserService userService;

    // 유저의 게시글 스크랩 리스트 조회
    @GetMapping("mypage/{userNo}/scrablist")
    public CommonResponse getScrabList(@PathVariable Long userNo){
        String resultMsg;
        String resultCode;

        try {
            List<ScrabResponseDto> response = scrabService.getScrabPosts(userNo);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, response);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    // 내가 쓴 글 목록
    @GetMapping("mypage/{userNo}/myposts")
    public CommonResponse getMyPosts(@PathVariable Long userNo, @RequestParam(value = "page") int pageNum, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "DESC") String sort){
        String resultMsg;
        String resultCode;

        try {
            Page<PostPagingResponseDto> response = postService.findAllPostsByUserPK(userNo,pageNum, size, sort);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, response);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    // 유저 프로필 수정 (내용 + 이미지)
    @PutMapping("mypage/{userNo}/profile/update")
    public CommonResponse updateProfile(@PathVariable Long userNo,
                                        @RequestPart(value = "profile", required = false) UserProfileUpdateRequest request,
                                        @RequestPart(value = "img", required = false) MultipartFile multipartFile){
        String resultMsg;
        String resultCode;

        try {
            userService.updateUserProfile(userNo, request, multipartFile);
            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_SUCCESS.getMessage();
            return new CommonResponse(resultCode, resultMsg);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.SAVE_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    // 프로필 이미지 업로드
    @PutMapping("mypage/{userNo}/profimg")
    public CommonResponse setProfileImg(@PathVariable Long userNo, @RequestPart(value = "img") MultipartFile multipartFile){
        String resultMsg;
        String resultCode;

        try {
            String response = s3Service.putProfImage(userNo, multipartFile);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, response);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }


    // 프로필 이미지 주소 조회
    @GetMapping("mypage/{userNo}/profimg")
    public CommonResponse getProfileImg(@PathVariable Long userNo){
        String resultMsg;
        String resultCode;

        try {
            String response = s3Service.getProfImage(userNo);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, response);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }
    }

    // 프로필 이미지 삭제
    @DeleteMapping("mypage/{userNo}/profimg")
    public CommonResponse deleteProfileImg(@PathVariable Long userNo){
        String resultMsg;
        String resultCode;

        try {
            s3Service.deleteProfImage(userNo);
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

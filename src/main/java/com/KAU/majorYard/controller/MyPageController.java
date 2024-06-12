package com.KAU.majorYard.controller;

import com.KAU.majorYard.dto.CommonResponse;
import com.KAU.majorYard.dto.CommonRestResult;
import com.KAU.majorYard.dto.response.PostPagingResponseDto;
import com.KAU.majorYard.dto.response.ScrabResponseDto;
import com.KAU.majorYard.service.PostServiceImpl;
import com.KAU.majorYard.service.ScrabService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final ScrabService scrabService;
    private final PostServiceImpl postService;

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
}

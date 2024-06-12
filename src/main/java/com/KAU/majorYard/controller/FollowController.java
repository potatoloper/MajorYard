//package com.KAU.majorYard.controller;
//
//import com.KAU.majorYard.dto.request.FollowRequestDto;
//import com.KAU.majorYard.dto.response.FollowResponseDto;
//import com.KAU.majorYard.entity.User;
//import com.KAU.majorYard.service.FollowService;
//import com.KAU.majorYard.service.UserService;
//import com.amazonaws.Response;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import static org.springframework.messaging.simp.SimpMessageHeaderAccessor.getUser;
//
//public class FollowController {
//
//    FollowService followService;
//
//    @PostMapping("/follow")
//    public Response<?> follow(@RequestBody FollowRequestDto followRequestDto, HttpServletRequest httpServletRequest) {
//        //Jwt 헤더에서 user 정보 가져오기
//        User user = getUser(httpServletRequest);
//
//        //Response로 한번 감싸서 return
//        return new Response<>("true","follow 성공",userService.follow(followRequestDto));
//    }
//
//}

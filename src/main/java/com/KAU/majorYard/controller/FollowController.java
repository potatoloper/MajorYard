package com.KAU.majorYard.controller;

import com.KAU.majorYard.dto.request.FollowRequestDto;
import com.KAU.majorYard.dto.response.UserDetailDto;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.service.FollowService;
import com.KAU.majorYard.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final UserService userService;

    @PostMapping("/follow")
    public ResponseEntity<?> follow(@RequestBody FollowRequestDto followRequestDto, HttpServletRequest httpServletRequest) {
        User user = userService.getUserFromRequest(httpServletRequest);
        return followService.followUser(followRequestDto.getFollowingId(), user);
    }

    @PostMapping("/unfollow")
    public ResponseEntity<?> unfollow(@RequestBody FollowRequestDto followRequestDto, HttpServletRequest httpServletRequest) {
        User user = userService.getUserFromRequest(httpServletRequest);
        return followService.unfollowUser(followRequestDto.getFollowingId(), user);
    }




    @GetMapping("/users/{userId}/followers")
    public ResponseEntity<List<UserDetailDto>> getFollowers(@PathVariable Long userId) {
        List<UserDetailDto> followers = followService.getFollowers(userId);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/users/{userId}/following")
    public ResponseEntity<List<UserDetailDto>> getFollowing(@PathVariable Long userId) {
        List<UserDetailDto> following = followService.getFollowing(userId);
        return ResponseEntity.ok(following);
    }



//    @GetMapping("/followers")
//    public ResponseEntity<List<String>> getFollowers(HttpServletRequest httpServletRequest) {
//        User user = userService.getUserFromRequest(httpServletRequest);
//        List<String> followers = followService.getFollowers(user.getId());
//        return ResponseEntity.ok(followers);
//    }



//    @GetMapping("/following")
//    public ResponseEntity<List<String>> getFollowing(HttpServletRequest httpServletRequest) {
//        User user = userService.getUserFromRequest(httpServletRequest);
//        List<String> following = followService.getFollowing(user.getId());
//        return ResponseEntity.ok(following);
//    }

}

//package com.KAU.majorYard.service;
//
//import com.KAU.majorYard.dto.request.FollowRequestDto;
//import com.KAU.majorYard.dto.response.FollowResponseDto;
//import com.KAU.majorYard.entity.Follow;
//import com.KAU.majorYard.entity.User;
//import com.KAU.majorYard.exception.CustomErrorCode;
//import com.KAU.majorYard.exception.CustomException;
//import com.KAU.majorYard.repository.FollowRepository;
//import com.KAU.majorYard.repository.UserRepository;
//import org.springframework.http.ResponseEntity;
//
//
//import java.util.Optional;
//
//public class FollowService {
//    private FollowRepository followRepository;
//
//    public ResponseEntity<String> followUser(Long followingId, User follower) {
//        if(followingId.equals(follower.getId())){
//            throw new CustomException(CustomErrorCode.SELF_FOLLOW);
//
//        }
//
//        Optional<Follow> checkFollow = followRepository.findByFollowingIdAndFollowerId(followingId, follower.getId());
//        if(checkFollow.isPresent()){
//            throw new GlobalCustomException(ExceptionType.ALREADY_FOLLOW);
//        }
//
//        User following = findUser(followingId);
//        Follow follow = new Follow(follower, following);
//        followRepository.save(follow);
//
//        return new ResponseEntity<>(following.getNickname() + "님을 팔로우하였습니다.", HttpStatus.OK);
//    }
//}

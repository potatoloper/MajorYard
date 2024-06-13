package com.KAU.majorYard.service;

import com.KAU.majorYard.dto.response.FollowResponseDto;
import com.KAU.majorYard.entity.Follow;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.exception.CustomErrorCode;
import com.KAU.majorYard.exception.CustomException;
import com.KAU.majorYard.repository.FollowRepository;
import com.KAU.majorYard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<String> followUser(Long followingId, User follower) {
        if (followingId.equals(follower.getId())) {
            throw new CustomException(CustomErrorCode.SELF_FOLLOW);
        }

        Optional<Follow> checkFollow = followRepository.findByFollowingIdAndFollowerId(followingId, follower.getId());
        if (checkFollow.isPresent()) {
            throw new CustomException(CustomErrorCode.ALREADY_FOLLOW);
        }

        User following = findUser(followingId);
        Follow follow = new Follow(follower, following);
        followRepository.save(follow);

        return new ResponseEntity<>(following.getNickName() + "님을 팔로우하였습니다.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> unfollowUser(Long followingId, User follower) {
        Follow follow = followRepository.findByFollowingIdAndFollowerId(followingId, follower.getId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_EXISTS_FOLLOWING));

        followRepository.delete(follow);

        User following = findUser(followingId);
        return new ResponseEntity<>(follower.getNickName() + "님이 " + following.getNickName() + "님의 팔로우를 취소하였습니다.", HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public List<String> getFollowers(Long userId) {
        List<Follow> followers = followRepository.findByFollowingId(userId);
        return followers.stream()
                .map(follow -> follow.getFollower().getNickName())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<String> getFollowing(Long userId) {
        List<Follow> followings = followRepository.findByFollowerId(userId);
        return followings.stream()
                .map(follow -> follow.getFollowing().getNickName())
                .collect(Collectors.toList());
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
    }
}

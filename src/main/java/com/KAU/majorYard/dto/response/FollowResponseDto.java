package com.KAU.majorYard.dto.response;

import com.KAU.majorYard.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FollowResponseDto {

    private Long userId;  // 요청자의 기본키(PK)
    private String nickname;  // 요청자의 닉네임
    private List<UserDetailDto> followers;  // 팔로워 목록, 각 팔로워의 PK와 닉네임 포함
    private List<UserDetailDto> followings;  // 팔로잉 목록, 각 팔로잉의 PK와 닉네임 포함

    @Builder
    public FollowResponseDto(Long userId, String nickname, List<UserDetailDto> followers, List<UserDetailDto> followings) {
        this.userId = userId;
        this.nickname = nickname;
        this.followers = followers;
        this.followings = followings;
    }

    public static FollowResponseDto toFollowResponseDto(User user) {
        List<UserDetailDto> followerDetails = user.getFollowers().stream()
                .map(follower -> UserDetailDto.builder()
                        .userId(follower.getFollower().getId())
                        .nickname(follower.getFollower().getNickName())
                        .build())
                .collect(Collectors.toList());

        List<UserDetailDto> followingDetails = user.getFollowings().stream()
                .map(following -> UserDetailDto.builder()
                        .userId(following.getFollowing().getId())
                        .nickname(following.getFollowing().getNickName())
                        .build())
                .collect(Collectors.toList());

        return FollowResponseDto.builder()
                .userId(user.getId())
                .nickname(user.getNickName())
                .followers(followerDetails)
                .followings(followingDetails)
                .build();
    }
}

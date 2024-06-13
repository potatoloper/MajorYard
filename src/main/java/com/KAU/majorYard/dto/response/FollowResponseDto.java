package com.KAU.majorYard.dto.response;

import com.KAU.majorYard.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FollowResponseDto {

    private Long id;
    private String nickname;
    private List<String> followers;
    private List<String> followings;

    @Builder
    public FollowResponseDto(Long id, String nickname, List<String> followers, List<String> followings) {
        this.id = id;
        this.nickname = nickname;
        this.followers = followers;
        this.followings = followings;
    }

    @Builder
    public static FollowResponseDto toFollowResponseDto(User user) {
        List<String> followerNames = user.getFollowers().stream()
                .map(follow -> follow.getFollower().getNickName())
                .collect(Collectors.toList());

        List<String> followingNames = user.getFollowings().stream()
                .map(follow -> follow.getFollowing().getNickName())
                .collect(Collectors.toList());

        return FollowResponseDto.builder()
                .id(user.getId())
                .nickname(user.getNickName())
                .followers(followerNames)
                .followings(followingNames)
                .build();
    }
}

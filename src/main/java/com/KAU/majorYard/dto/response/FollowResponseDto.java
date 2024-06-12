//package com.KAU.majorYard.dto.response;
//
//import com.KAU.majorYard.entity.User;
//import lombok.Builder;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class FollowResponseDto {
//
//    // pk 값을 id로 받음. 유저 로그인 id를 고유값으로 만들기 위함.
//    private Long id;
//
//    // 유저 닉네임
//    private String nickname;
//
//    // 팔로워 리스트
//    private List<String> followers = new ArrayList<>();
//
//    // 팔로잉 리스트
//    private List<String> followings = new ArrayList<>();
//
//    // 생성자
//    @Builder
//    public FollowResponseDto(Long id, String nickname, List<String> followers, List<String> followings) {
//        this.id = id;
//        this.nickname = nickname;
//        this.followers = followers;
//        this.followings = followings;
//    }
//
//
//
//    // user 인스턴스를 UserResponseDto로 변환시키는 로직
//    @Builder
//    public static FollowResponseDto toFollowResponseDto(User user) {
//
//        // lambda를 통해 List
//        List<String> followerNames = user.getFollowers().stream()
//                .map(follow -> follow.getFollowing().getNickname())
//                .collect(Collectors.toList());
//
//        List<String> followingNames = user.getFollowings().stream()
//                .map(follow -> follow.getFollower().getNickname())
//                .collect(Collectors.toList());
//
//        return new UserResponseDto(
//                user.getId(),
//                user.getNickName(),
//                followerNames,
//                followingNames
//        );
//}
//
//

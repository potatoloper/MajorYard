package com.KAU.majorYard.dto.request;

public class FollowRequestDto {
    private Long followingId;
    private Long userId;

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }
}

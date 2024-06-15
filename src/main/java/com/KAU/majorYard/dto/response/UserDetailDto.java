package com.KAU.majorYard.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class UserDetailDto {
    private Long userId;  // 사용자의 기본키(PK)
    private String nickname;  // 사용자의 닉네임


    @Builder
    public UserDetailDto(Long userId, String nickname){
        this.userId = userId;
        this.nickname = nickname;
    }

}

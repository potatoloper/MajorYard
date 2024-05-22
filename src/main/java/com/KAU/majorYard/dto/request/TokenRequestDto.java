package com.KAU.majorYard.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenDto {
    private String grantType; // 토큰 발급시 사용되는 인증 방식
    private String accessToken;
    private Long accessTokenExpiresIn;
    private String refreshToken;
}

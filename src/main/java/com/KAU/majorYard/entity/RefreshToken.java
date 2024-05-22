package com.KAU.majorYard.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.UUID;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class UserRefreshToken {
    @Id
    private UUID userId;
    @OneToOne(fetch = FetchType.LAZY) // 회원 1명당 1개의 리프래시 토큰을 가짐
    @MapsId
    @JoinColumn(name = "user_id") // RefreshToken:User = 1:1
    private User user;
    private String refreshToken;
    private int reissueCount = 0; // 재발급 횟수를 제한할 것이기 때문에 리프레시 토큰마다 재발급 횟수를 저장할 프로퍼티



    public UserRefreshToken(User user,String refreshToken){
        this.user = user;
        this.refreshToken = refreshToken;
    }

    public void updateUserRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

    public void validateRefreshToken(String refreshToken){
        this.refreshToken.equals(refreshToken);
    }

    public void increaseReissueCount(){
        reissueCount++;
    }


}

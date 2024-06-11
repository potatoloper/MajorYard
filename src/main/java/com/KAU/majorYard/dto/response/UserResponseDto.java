package com.KAU.majorYard.dto.response;

import com.KAU.majorYard.entity.User;
import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseBody;

@Getter
public class UserResponseDto {
    private final Long id;
    private final String userLoginId;
    private final String nickName;


    private UserResponseDto(Long id, String userLoginId, String nickName) {
        this.id  = id;
        this.userLoginId = userLoginId;
        this.nickName = nickName;
    }

    public static UserResponseDto from(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getLoginId(),
                user.getNickName()
        );
    }


}



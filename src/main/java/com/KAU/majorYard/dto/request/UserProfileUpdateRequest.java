package com.KAU.majorYard.dto.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileUpdateRequest {
    private String loginId;
    private String nickName;
    private String userPhone;
}

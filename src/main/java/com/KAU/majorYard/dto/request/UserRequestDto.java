package com.KAU.majorYard.dto.request;

import com.KAU.majorYard.entity.BaseEntity;
import com.KAU.majorYard.entity.majorYard_enum.Gender;
import com.KAU.majorYard.entity.majorYard_enum.Role;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto extends BaseEntity {

    private Long id;    // 회원 번호 (PK)
    private String loginId;
    private String userName;
    private String nickName;
    private String password;
    private String checkedPassword; // 회원가입 시 필요
    private String userPhone;
    private Gender gender;
    private String userBirth;
    private DepartmentDto department;
    private String schoolEmail;
    private Role role;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DepartmentDto {
        private Long departmentId;
        private String departmentName;
    }
}

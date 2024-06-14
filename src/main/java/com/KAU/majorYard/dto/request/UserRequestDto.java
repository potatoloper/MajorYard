package com.KAU.majorYard.dto.request;

import com.KAU.majorYard.entity.BaseEntity;
import com.KAU.majorYard.entity.majorYard_enum.Gender;
import com.KAU.majorYard.entity.majorYard_enum.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto extends BaseEntity {

    private Long id;    // 회원 번호 (PK)


    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$",
            message = "아이디는 5글자 이상이며, 영어와 숫자가 모두 포함되어야 합니다.")
    private String loginId;

    @NotBlank(message = "이름을 입력해주세요.")
    private String userName;
    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, message = "닉네임이 너무 짧습니다.")
    private String nickName;
    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
            message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String password;
    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    private String checkedPassword; // 회원가입 시 필요
    private String userPhone;
    private Gender gender;
    private String userBirth;
    private DepartmentDto department;


    @NotBlank(message = "학교 이메일을 입력해주세요.")
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

package com.KAU.majorYard.dto.request;

import com.KAU.majorYard.entity.Department;
import com.KAU.majorYard.entity.majorYard_enum.Role;
import com.KAU.majorYard.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
public class UserSignupRequestDto {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String loginId;

    @NonNull
    private String userName; // 사용자 이름


    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min=2, message = "닉네임이 너무 짧습니다.")
    private String nickName; // 사용자 닉네임

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
            message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String password;

    @NonNull
    private String checkedPassword;

    @NonNull
    private String userPhone;

    @NonNull
    private Department department;

    @NonNull
    private String schoolEmail;

    private Role role;


    public User toEntity(){
        return User.builder()
                .loginId(loginId)
                .userName(userName)
                .password(password)
                .userPhone(userPhone)
                .department(department)
                .schoolEmail(schoolEmail)
                .role(role)
                .build();

    }

}

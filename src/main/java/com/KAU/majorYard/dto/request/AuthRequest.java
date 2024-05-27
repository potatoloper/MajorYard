package com.KAU.majorYard.dto.request;

import com.KAU.majorYard.entity.Department;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.entity.majorYard_enum.Gender;
import com.KAU.majorYard.entity.majorYard_enum.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;


@Data
@AllArgsConstructor
//@NoArgsConstructor
public class AuthRequest {


    @Data
    @AllArgsConstructor
    public static class adminSignUp{
        private String id;
        private String password;

    }
    @Data
    @AllArgsConstructor
    public static class userSignUp {

        private String id;
        @NotBlank(message = "아이디를 입력해주세요.")
        private String loginId;

        @NotBlank(message = "이름을 입력해주세요.")
        private String userName;

        @NotBlank(message = "닉네임을 입력해주세요.")
        @Size(min=2, message = "닉네임이 너무 짧습니다.")
        private String nickName;

        @NotBlank(message = "비밀번호를 입력해주세요")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
                message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
        private String password;

        @NotNull
        private String checkedPassword;

        @NotNull
        private String userPhone;

        @NotNull
        private Gender gender;

        @NotNull
        private String userBirth;

        @NotNull
        private DepartmentRequest department;

        @NotNull
        private String schoolEmail;

        @NotNull
        private Role role;


        // SpringSecurity에서 사용자의 정보를 담는
        @Builder
        public User toUser(PasswordEncoder passwordEncoder, Department department) {
            return User.builder()
                    .loginId(this.getLoginId())
                    .userName(this.getUserName())
                    .nickName(this.getNickName())
                    .password(passwordEncoder.encode(this.getPassword()))
                    .userPhone(this.getUserPhone())
                    .gender(this.getGender())
                    .userBirth(this.getUserBirth())
                    .department(department)
                    .schoolEmail(this.getSchoolEmail())
                    .role(role != null ? role : Role.ROLE_USER)
                    .build();
        }

        public boolean isPasswordValid() {
            return this.password != null && this.password.equals(this.checkedPassword);
        }
    }


    @Data
    @AllArgsConstructor
    public static class userSignIn {
        @NotBlank(message = "로그인 ID는 필수입니다.")
        private String loginId;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;

        public UsernamePasswordAuthenticationToken toAuthenticationToken() {
            return new UsernamePasswordAuthenticationToken(this.loginId, this.password);
        }
    }

    @Data
    @AllArgsConstructor
    public static class DepartmentRequest {
        @NotNull
        private Long departmentId;

        @NotNull
        private String name;
    }
}

















package com.KAU.majorYard.dto.response;

import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.entity.majorYard_enum.Gender;
import com.KAU.majorYard.entity.majorYard_enum.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String loginId;
    private String userName;
    private String nickName;
    private String userPhone;
    private Gender gender;
    private String userBirth;
    private DepartmentDto department;
    private String schoolEmail;
    private Role role;

    @Getter
    @NoArgsConstructor
    public static class DepartmentDto {
        private Long departmentId;
        private String departmentName;

        public DepartmentDto(Long departmentId, String departmentName) {
            this.departmentId = departmentId;
            this.departmentName = departmentName;
        }
    }

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.loginId = user.getLoginId();
        this.userName = user.getUserName();
        this.nickName = user.getNickName();
        this.userPhone = user.getUserPhone();
        this.gender = user.getGender();
        this.userBirth = user.getUserBirth();
        if (user.getDepartment() != null) {
            this.department = new DepartmentDto(user.getDepartment().getId(), user.getDepartment().getDepartmentName());
        }
        this.schoolEmail = user.getSchoolEmail();
        this.role = user.getRole();
    }

    public static UserResponseDto from(User user) {
        return new UserResponseDto(user);
    }
}

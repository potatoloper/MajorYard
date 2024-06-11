package com.KAU.majorYard.service;

import com.KAU.majorYard.dto.request.UserRequestDto;
import com.KAU.majorYard.dto.response.UserResponseDto;
import com.KAU.majorYard.entity.Department;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.entity.majorYard_enum.Gender;
import com.KAU.majorYard.entity.majorYard_enum.Role;
import com.KAU.majorYard.exception.CustomErrorCode;
import com.KAU.majorYard.exception.CustomException;
import com.KAU.majorYard.repository.DepartmentRepository;
import com.KAU.majorYard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public boolean register(UserRequestDto credentials) {
        if (!credentials.getPassword().equals(credentials.getCheckedPassword())) {
            throw new CustomException(CustomErrorCode.INVALID_PASSWORD);
        }

        if (userRepository.existsByLoginId(credentials.getLoginId())) {
            throw new CustomException(CustomErrorCode.DUPLICATE_USER);
        }

        Department department = departmentRepository.findById(credentials.getDepartment().getDepartmentId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.INVALID_DEPARTMENT_ID));

        User newUser = User.builder()
                .loginId(credentials.getLoginId())
                .userName(credentials.getUserName())
                .nickName(credentials.getNickName())
                .password(credentials.getPassword())
                .userPhone(credentials.getUserPhone())
                .gender(credentials.getGender())
                .userBirth(credentials.getUserBirth())
                .schoolEmail(credentials.getSchoolEmail())
                .role(credentials.getRole())
                .department(department)
                .build();

        userRepository.save(newUser);  // 새 사용자 저장
        return true;
    }

    public UserResponseDto login(String loginId, String password) {
        User user = userRepository.findByLoginId(loginId);
        if (user != null && user.getPassword().equals(password)) {
            return UserResponseDto.from(user);  // 로그인 성공
        }
        throw new CustomException(CustomErrorCode.USER_NOT_FOUND); // 로그인 실패
    }

    public void logout(){
        System.out.println("로그아웃 되었습니다.");
    }
}

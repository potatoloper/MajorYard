package com.KAU.majorYard.service;


import com.KAU.majorYard.repository.UserRepository;
import com.KAU.majorYard.dto.request.UserSignupRequestDto;
import com.KAU.majorYard.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Long signUp(UserSignupRequestDto userSignupRequestDto) throws Exception {

        if(userRepository.findByLoginId(userSignupRequestDto.getLoginId()).isPresent()){
            throw new Exception("이미 존재하는 아이디입니다.");
        }

        if(!userSignupRequestDto.getPassword().equals(userSignupRequestDto.getCheckedPassword())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        User user = userRepository.save(userSignupRequestDto.toEntity());
        user.encodePassword(passwordEncoder);


        user.addUserAuthority();
        return user.getId();
    }
}

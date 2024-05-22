package com.KAU.majorYard.service;


import com.KAU.majorYard.entity.PrincipalDetails;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.exception.CustomErrorCode;
import com.KAU.majorYard.exception.CustomException;
import com.KAU.majorYard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public PrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public PrincipalDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자 정보 조회
        User userInfo = userRepository.findByLoginId(username).orElseThrow(() ->
                new CustomException(CustomErrorCode.USER_NOT_FOUND)
        );

        // PrincipalDetails 객체 반환
        return new PrincipalDetails(userInfo);
    }
}

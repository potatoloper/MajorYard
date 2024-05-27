package com.KAU.majorYard.service;

import com.KAU.majorYard.entity.PrincipalDetails;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.exception.CustomErrorCode;
import com.KAU.majorYard.exception.CustomException;
import com.KAU.majorYard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * UserDetailsService는 유저의 정보를 가져오는 인터페이스이며, 기본적으로 loadUserByUsername 메소드를 오버라이드 해야한다.
 * loadUserByUsername 메소드의 리턴 타입은 UserDetails이며, 유저의 정보를 불러와서 UserDetails 타입으로 리턴한다.
 * */

@Service("userDetailsService")
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(PrincipalDetailsService.class);

    @Autowired
    public PrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public PrincipalDetails loadUserByUsername(String userLoginId) throws UsernameNotFoundException {
        // 사용자 정보 조회
        Optional<User> userOptional = userRepository.findByLoginId(userLoginId);
        if (!userOptional.isPresent()) {
            logger.error("사용자 이름으로 사용자를 찾을 수 없습니다: {}", userLoginId);
            throw new CustomException(CustomErrorCode.USER_NOT_FOUND);
        }
        User userInfo = userOptional.get();

        // 권한 준비
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userInfo.getRole().name()));

        // 로그인 시도된 사용자의 정보를 로깅
        logger.info("사용자 이름으로 사용자를 불러왔습니다: {}, 역할: {}", userInfo.getLoginId(), userInfo.getRole().name());

        // PrincipalDetails 객체 반환
        return new PrincipalDetails(userInfo, authorities);
    }
}

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

import java.util.ArrayList;
import java.util.List;


/*
* UserDetailsService는 유저의 정보를 가져오는 인터페이스이며, 기본적으로 loadUserByUsername 메소드를 오버라이드 해야한다.
* loadUserByUsername 메소드의 리턴 타입은 UserDetails이며, 유저의 정보를 불러와서 UserDetails 타입으로 리턴한다.
* */

@Service("userDetailsService")
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

        // 권한 준비
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 역할을 "ROLE_" 접두사가 붙은 문자열로 변환
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userInfo.getRole().name()));


        // PrincipalDetails 객체 반환
        return new PrincipalDetails(userInfo, authorities);
    }
}

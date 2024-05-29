//package com.KAU.majorYard.security;
//
//import com.KAU.majorYard.entity.PrincipalDetails;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@RequiredArgsConstructor
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    private final UserDetailsService userDetailsService;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = (String) authentication.getCredentials();
//
//        // 사용자 정보를 캡술화하며 인증정보가 포함됨
//        PrincipalDetails principalDetails = (PrincipalDetails) userDetailsService.loadUserByUsername(username);
//
//        // 비밀번호 검증 (입력된 비밀번호와 저장된 해시가 일치하는지 확인)
//        if (!passwordEncoder.matches(password, principalDetails.getPassword())) {
//            throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.");
//        }
//
//        // 인증 토큰 생성
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                principalDetails, null, principalDetails.getAuthorities()
//        );
//
//        return authenticationToken;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//}

package com.KAU.majorYard.service;

import com.KAU.majorYard.dto.request.AuthRequest;
import com.KAU.majorYard.dto.request.TokenRequestDto;
import com.KAU.majorYard.entity.Department;
import com.KAU.majorYard.entity.RefreshToken;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.entity.majorYard_enum.Role;
import com.KAU.majorYard.exception.CustomErrorCode;
import com.KAU.majorYard.exception.CustomException;
import com.KAU.majorYard.security.TokenProvider;
import com.KAU.majorYard.repository.DepartmentRepository;
import com.KAU.majorYard.repository.RefreshTokenRepository;
import com.KAU.majorYard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;

    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public Long signup(AuthRequest.userSignUp userSignUp, PasswordEncoder passwordEncoder ) {
        if (userRepository.findByLoginId(userSignUp.getLoginId()).isPresent()) {
            throw new CustomException(CustomErrorCode.DUPLICATE_USER);
        }


        // departmentId가 null인지 확인
        Long departmentId = userSignUp.getDepartment().getDepartmentId();
        if (departmentId == null) {
            throw new IllegalArgumentException("Department ID must not be null");
        }

        String encodedPassword = passwordEncoder.encode(userSignUp.getPassword());

        // departmentId로 Department를 조회하여 영속 상태로 전환
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid department ID"));

        // 유저 엔티티를 생성하고, department와 권한을 설정
        User user = userSignUp.toUser(passwordEncoder, department);
        user.setRole(Role.ROLE_USER);


        // 유저 엔티티를 저장
        user = userRepository.save(user);
        return user.getId(); // 사용자 ID를 Long으로 반환
    }


    @Transactional
    public TokenRequestDto login(AuthRequest.userSignIn userSignIn) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userSignIn.getLoginId(), userSignIn.getPassword());


        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenRequestDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return tokenDto;
    }
    @Transactional
    public TokenRequestDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenRequestDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken .updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }
}
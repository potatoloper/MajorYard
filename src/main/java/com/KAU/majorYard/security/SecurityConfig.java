package com.KAU.majorYard.security;

import com.KAU.majorYard.entity.majorYard_enum.Role;
import com.KAU.majorYard.jwt.JwtAccessDeniedHandler;
import com.KAU.majorYard.jwt.JwtAuthenticationEntryPoint;
import com.KAU.majorYard.security.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import java.io.PrintWriter;

@EnableWebSecurity(debug = true) //TODO: 테스트용 디버깅 모드이므로 나중에 꼭 지워야 함
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;


    // AuthenticationManger 빈 생성 시 자동으로 UserSecurityService와 PasswordEncoder가 설정된다.
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /*
     * HTTP에 대해서 '인증'과 '인가'를 담당하는 메서드
     * 필터를 통해 인증 방식과 인증 절차에 대해서 등록하며 설정을 담당하는 메서드
     * */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(httpBasic -> httpBasic.disable()) // 기본 HTTP 인증 비활성화
                /* 1번 */ // CSRF 보호 기능 비활성화
                .csrf(csrf -> csrf.disable())
                .headers((headerConfig) -> headerConfig.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()))  // X-Frame-Options 비활성화
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 관리를 무상태로 설정

                /* 2번 */
                .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/auth/signup").permitAll() // 회원가입은 인증 없이 접근 허용
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // 로그인은 인증 없이 접근 허용
                        .requestMatchers(HttpMethod.POST, "/auth/**").hasAnyRole("ADMIN") // /auth/** 경로는 ADMIN 권한을 가진 사용자만 접근 가능
                        .requestMatchers(HttpMethod.POST, "/posts/**").hasAnyAuthority("USER") // /posts/** 경로는 USER 권한을 가진 사용자만 접근 가능
                        .anyRequest().authenticated() // 그 외의 모든 요청은 인증 필요
                )

                /* 3번 */
                // 인증 실패 시 처리 ( 401 403 관련 예외처리)
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 인증 실패 시 처리
                        .accessDeniedHandler(jwtAccessDeniedHandler)) // 권한 부족 시 처리
                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class) // JWT 필터 추가
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class); // CORS 필터 추가


        // TODO: formlogin.disable => springsecurity jwt , restapi

        return http.build();
    }

    private final AuthenticationEntryPoint unauthorizedEntryPoint = (request, response, authException) -> {
        ErrorResponse fail = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Spring security unauthorized...");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        String json = new ObjectMapper().writeValueAsString(fail);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
    };

    private final AccessDeniedHandler accessDeniedHandler = (request, response, accessDeniedException) -> {
        ErrorResponse fail = new ErrorResponse(HttpStatus.FORBIDDEN, "Spring security forbidden...");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        String json = new ObjectMapper().writeValueAsString(fail);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
    };

    @Getter
    @RequiredArgsConstructor
    public class ErrorResponse {

        private final HttpStatus status;
        private final String message;
    }
}



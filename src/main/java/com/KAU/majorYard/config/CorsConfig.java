package com.KAU.majorYard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/*프론트엔드, 백엔드를 구분지어서 개발하거나, 서로 다른 Server 환경에서 자원을 공유할 때,
Cors설정이 안 되어있으면 오류가 발생한다. 이를 방지하기 위해 Cors 설정을 해줌*/


@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000"); // TODO: 실제 도메인으로 변경해야 함.
        config.addAllowedHeader("*"); //TODO: 실제 사용하는 헤더만 추가
        config.addAllowedMethod("*"); // TODO: 필요한 메서드만 추가

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

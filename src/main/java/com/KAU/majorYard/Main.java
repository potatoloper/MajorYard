package com.KAU.majorYard;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@EnableJpaAuditing // 회원가입시 유저 생성시간과 수정시간 추가
@EntityScan(basePackages = "com.KAU.majorYard.entity")
@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

	}
	// 현재 호스트ip와 접근ip를 리턴하는 메소드
	@GetMapping("/ip")
	public Map<String, String> ip(HttpServletRequest request) throws UnknownHostException {
		return new HashMap<>() {{
			put("hostIp", InetAddress.getLocalHost().getHostAddress());
			put("accessIp", request.getHeader("x-forwarded-for"));
		}};
	}

}

package com.KAU.majorYard.controller;

import com.KAU.majorYard.dto.request.AuthRequest;
import com.KAU.majorYard.dto.request.TokenRequestDto;
import com.KAU.majorYard.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@RequestBody AuthRequest.userSignUp userSignUp) {
        Long userId = authService.signup(userSignUp, passwordEncoder);
        return ResponseEntity.ok(userId);
    }


    @PostMapping("/login")
    public ResponseEntity<TokenRequestDto> login(@RequestBody AuthRequest.userSignIn userSignIn) {
        return ResponseEntity.ok(authService.login(userSignIn));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenRequestDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}
package com.KAU.majorYard.controller;

import com.KAU.majorYard.dto.request.UserRequestDto;
import com.KAU.majorYard.dto.response.UserResponseDto;
import com.KAU.majorYard.exception.CustomErrorCode;
import com.KAU.majorYard.exception.CustomException;
import com.KAU.majorYard.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public @ResponseBody String register(@RequestBody UserRequestDto credentials) {
        try {
            boolean success = userService.register(credentials);
            if (!success) {
                throw new CustomException(CustomErrorCode.DUPLICATE_USER);
            }
            return "Registration successful";
        } catch (CustomException e) {
            throw new ResponseStatusException(e.getCustomErrorCode().getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("/login")
    public @ResponseBody UserResponseDto login(@RequestBody UserRequestDto credentials, HttpServletRequest request) {
        try {
            UserResponseDto userResponseDto = userService.login(credentials.getLoginId(), credentials.getPassword());
            HttpSession session = request.getSession(true); // 세션 생성
            session.setAttribute("userId", userResponseDto.getId()); // 세션에 사용자 ID 저장
            session.setAttribute("userLoginId", userResponseDto.getLoginId()); // 세션에 사용자 Login ID 저장
            return userResponseDto;
        } catch (CustomException e) {
            throw new ResponseStatusException(e.getCustomErrorCode().getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("/logout")
    public @ResponseBody String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 세션이 있으면 가져옴
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return "Logout successful";
    }
}

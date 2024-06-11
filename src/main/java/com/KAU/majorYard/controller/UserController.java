package com.KAU.majorYard.controller;

import com.KAU.majorYard.dto.request.UserRequestDto;
import com.KAU.majorYard.dto.response.UserResponseDto;
import com.KAU.majorYard.exception.CustomErrorCode;
import com.KAU.majorYard.exception.CustomException;
import com.KAU.majorYard.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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
    public @ResponseBody UserResponseDto login(@RequestBody UserRequestDto credentials) {
        try {
            return userService.login(credentials.getLoginId(), credentials.getPassword());
        } catch (CustomException e) {
            throw new ResponseStatusException(e.getCustomErrorCode().getHttpStatus(), e.getMessage());
        }
    }
}

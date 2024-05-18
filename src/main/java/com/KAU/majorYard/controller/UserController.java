package com.KAU.majorYard.controller;

import com.KAU.majorYard.dto.request.UserSignupRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.KAU.majorYard.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public Long join(@Valid @RequestBody UserSignupRequestDto request) throws Exception {
        return userService.signUp(request);
    }
}

package com.KAU.majorYard.service;

import com.KAU.majorYard.dto.request.UserSignupRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    // 회원가입
    Long signUp(UserSignupRequestDto userSignupRequestDto) throws Exception;



}
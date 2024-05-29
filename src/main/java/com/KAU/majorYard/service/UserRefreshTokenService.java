//package com.KAU.majorYard.service;
//
//import com.KAU.majorYard.entity.RefreshToken;
//import com.KAU.majorYard.repository.RefreshTokenRepository;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//@Service
//@Slf4j
//@AllArgsConstructor
//@Transactional(readOnly = true)
//public class UserRefreshTokenService {
//
//    private final RefreshTokenRepository userRefreshTokenRepository;
//
//
//    @Transactional
//    public void saveRefreshToken(String key, RefreshToken authRefreshToken){
//        // RefreshToken 찾거나 새로 만들기
//        RefreshToken userRefreshToken = userRefreshTokenRepository.findByKey(key)
//                .map(refreshToken -> refreshToken.updateValue(authRefreshToken.getValue()))
//                .orElse(new RefreshToken(key, authRefreshToken.getValue()));
//
//        userRefreshTokenRepository.save(userRefreshToken);
//    }
//
//
//    @Transactional
//    public void deleteRefreshToken(String id) {
//        Optional<RefreshToken> optionalUserRefreshToken = userRefreshTokenRepository.findByKey(id);
//
//        // refreshToken 삭제
//        optionalUserRefreshToken.ifPresent(userRefreshTokenRepository::delete);
//    }
//}

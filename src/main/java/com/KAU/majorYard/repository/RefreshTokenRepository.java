package com.KAU.majorYard.repository;

import com.KAU.majorYard.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/*
* 소유자의 ID가 id면서 재발급 횟수가 count보다 작은 MemberRefreshToken 객체를 반환
* */
@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, UUID>{
    Optional<UserRefreshToken> findUserIdAndReissueCountLessThan(UUID id, Long count);
}
package com.KAU.majorYard.repository;

import com.KAU.majorYard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    User findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);
    boolean existsByNickName(String nickName);

}


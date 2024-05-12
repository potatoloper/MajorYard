package com.KAU.majorYard.repository;

import com.KAU.majorYard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByLoginId(String login_id);
}
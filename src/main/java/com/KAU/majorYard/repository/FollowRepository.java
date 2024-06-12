package com.KAU.majorYard.repository;

import com.KAU.majorYard.entity.Follow;
import com.KAU.majorYard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFollowingIdAndFollowerId(Long followingId, Long id);
}
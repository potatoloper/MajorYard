package com.KAU.majorYard.repository;

import com.KAU.majorYard.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowingIdAndFollowerId(Long followingId, Long followerId);
    List<Follow> findByFollowingId(Long followingId);
    List<Follow> findByFollowerId(Long followerId);
}

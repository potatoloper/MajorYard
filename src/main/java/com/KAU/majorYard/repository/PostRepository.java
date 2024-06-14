package com.KAU.majorYard.repository;

import com.KAU.majorYard.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByBoardId(Long board_no, Pageable pageable);
    Page<Post> findAllByUserId(Long user_no, Pageable pageable);
    List<Post> findAllByUserId(Long user_no);
    Page<Post> findByUserIdIn(List<Long> user_no, Pageable pageable);

}

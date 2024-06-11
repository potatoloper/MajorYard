package com.KAU.majorYard.repository;

import com.KAU.majorYard.entity.Img;
import com.KAU.majorYard.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImgRepository extends JpaRepository<Img, Long> {
    List<Img> findAllByPostId(Long post_no);
}

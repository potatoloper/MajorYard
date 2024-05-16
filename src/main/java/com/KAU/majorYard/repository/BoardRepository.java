package com.KAU.majorYard.repository;

import com.KAU.majorYard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}

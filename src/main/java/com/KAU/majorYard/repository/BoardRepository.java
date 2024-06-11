package com.KAU.majorYard.repository;

import com.KAU.majorYard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByDepartmentId(Long department_no);
}

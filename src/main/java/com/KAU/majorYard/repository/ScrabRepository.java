package com.KAU.majorYard.repository;

import com.KAU.majorYard.entity.Scrab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrabRepository extends JpaRepository<Scrab, Long> {
    List<Scrab> findAllByUserId (Long user_no);
}
package com.KAU.majorYard.repository;

import com.KAU.majorYard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {

}
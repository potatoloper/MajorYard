package com.KAU.majorYard.repository;



import com.KAU.majorYard.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

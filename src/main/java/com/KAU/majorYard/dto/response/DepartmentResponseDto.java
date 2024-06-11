package com.KAU.majorYard.dto.response;

import com.KAU.majorYard.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DepartmentResponseDto {
    private Long id;
    private String departmentName;
    private String majorCategory;

    public DepartmentResponseDto(Department department){
        this.id = department.getId();
        this.departmentName = department.getDepartmentName();
        this.majorCategory = department.getMajorCategory().toString();
    }
}

package entity;

import entity.majorYard_enum.MajorCategory;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "Department")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // department_no

    private String department_name;

    @Enumerated(value = EnumType.STRING)
    private MajorCategory major_category; // 대분류 정보 추가

    public Department(String department_name, MajorCategory major_category) {
        this.department_name = department_name;
        this.major_category = major_category;
    }

}

package entity;

import entity.majorYard_enum.MajorCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Department")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="department_no")
    private Long id; // department_no

    private String department_name;

    @Enumerated(value = EnumType.STRING)
    private MajorCategory major_category; // 대분류 정보 추가

    public Department(String department_name, MajorCategory major_category) {
        this.department_name = department_name;
        this.major_category = major_category;
    }

    //    Department:Board = 1:N
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Board> departmentBoards = new ArrayList<>();



    //    Department:User = 1:1
    @OneToOne(mappedBy = "department")
    private User user;





}

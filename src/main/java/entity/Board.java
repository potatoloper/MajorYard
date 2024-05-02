package entity;


import entity.majorYard_enum.BoardName;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "Board")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // board_no

    @Enumerated(value = EnumType.STRING)
    private BoardName board_name;

}

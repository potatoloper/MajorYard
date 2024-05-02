package entity;

import entity.majorYard_enum.Follw;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "Follow")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // follow_no

    @Enumerated(value = EnumType.STRING)
    private Follw follw;


}

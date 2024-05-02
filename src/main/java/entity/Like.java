package entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Like")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // like_no


}

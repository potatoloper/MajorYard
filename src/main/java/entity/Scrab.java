package entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Scrab")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scrab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // scrab_no


}

package entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Img")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Img {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // img_no

    private String img_file_name;
}

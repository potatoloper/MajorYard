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
    @Column(name="img_no")
    private Long id; // img_no

    private String stored_file_name;

    private String original_file_name;

    private long file_size;

    // Img:Post = N:1
    @ManyToOne
    @JoinColumn(name = "post_no")
    private Post post;
}

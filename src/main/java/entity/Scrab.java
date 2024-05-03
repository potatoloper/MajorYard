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
    @Column(name="scrab_no")
    private Long id; // scrab_no

    // Scrab:User = N:1
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    // Scrab:Post = 1:1
    @OneToOne(mappedBy = "scrab")
    private Post post;




}

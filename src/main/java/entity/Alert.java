package entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Alert")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="alert_no")
    private Long id; // alert_no

    // Alert:Comment = N:1
    @ManyToOne
    @JoinColumn(name = "comment_no")
    private Comment comment;

    // Alert:Post = N:1
    @ManyToOne
    @JoinColumn(name = "post_no")
    private Post post;

}

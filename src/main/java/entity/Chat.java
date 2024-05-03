package entity;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "Chat")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chat_no")
    private Long id; // chat_no

    private String partner;

    private String chat_contents;

    // Chat : User = N:1
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;


}

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
    private Long id; // chat_no

    private String partner;

    private String chat_contents;

}

package com.KAU.majorYard.entity;

import com.KAU.majorYard.entity.majorYard_enum.Follw;
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
    @Column(name="follow_no")
    private Long id; // follow_no

    @Enumerated(value = EnumType.STRING)
    private Follw follw;

    // Follow:User = N:1
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;


}

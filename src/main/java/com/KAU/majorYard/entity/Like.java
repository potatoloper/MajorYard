package com.KAU.majorYard.entity;

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
    @Column(name="like_no")
    private Long id; // like_no

    // Like : User = N:1
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    // Like:Post = N:1
    @ManyToOne
    @JoinColumn(name = "post_no")
    private Post post;


}

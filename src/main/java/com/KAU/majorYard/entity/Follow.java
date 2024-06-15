package com.KAU.majorYard.entity;

import com.KAU.majorYard.entity.majorYard_enum.Follw;
import com.fasterxml.jackson.annotation.JsonIgnore;
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



    // 양방향 맵핑
    @JsonIgnore // 무한 참조를 방지
    @ManyToOne(fetch = FetchType.LAZY) // 성능 최적화를 위함.
    @JoinColumn(name = "follower_id")
    private User follower;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private User following;

    @Builder
    public Follow(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }



}

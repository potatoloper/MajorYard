package com.KAU.majorYard.entity;

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

    private String storedFileName;

    private String originalFileName;

    private long fileSize;

    // Img:Post = N:1
    @ManyToOne
    @JoinColumn(name = "post_no")
    private Post post;
}

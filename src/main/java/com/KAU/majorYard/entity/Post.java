package com.KAU.majorYard.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Post")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_no")
    private Long id; // post_no

    private String postTitle;
    private String postContent;
    private String postLike;

    private String postScrab;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime postCreatedDt;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime postModifiedDt;


    // Post:Img = 1:N
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Img> postImgs = new ArrayList<>();


    // 주테이블(Post) 외래키 단방향
    // Post:Scarb =1:1
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "scrab_no")
    private Scrab scrab;

    // Post:Alert = 1:N
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Alert> postAlerts = new ArrayList<>();

    // Post:Like = 1:N
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Like> postLikes = new ArrayList<>();

    // Post:Comment = 1:N
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> postComments = new ArrayList<>();



    // Post : User = N:1
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;


    // Post:Board = N:1
    @ManyToOne
    @JoinColumn(name = "board_no")
    private Board board;





}

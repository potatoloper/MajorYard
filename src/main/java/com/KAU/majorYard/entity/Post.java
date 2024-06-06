package com.KAU.majorYard.entity;



import com.KAU.majorYard.entity.majorYard_enum.Answered;
import com.KAU.majorYard.entity.majorYard_enum.PostType;

import com.fasterxml.jackson.annotation.JsonManagedReference;



import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Table(name = "Post")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_no")
    private Long id; // post_no

    private String postTitle;
    @Column(columnDefinition = "varchar(4000)")
    private String postContent;
    private int postLike;
    private int postScrab;
    private int postcomment;

    // 홍보 게시판에서만 사용
    @Enumerated(value = EnumType.STRING)
    private PostType postType;

    // 질문 게시판에서만 사용
    @Enumerated(value = EnumType.STRING)
    private Answered answered;

    // 스터디 게시판에서만 사용
    private String studyRegion;
    private Integer studyPartyOf;
    private Integer studyFee;
    private String studyUntil;

    // 시사이슈 게시판에서만 사용
    @Column(columnDefinition = "varchar(1000)")
    private String url;


//    @CreatedDate
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime postCreatedDt;
//
//    @LastModifiedDate
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime postModifiedDt;


    // Post:Img = 1:N
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<Img> postImgs = new ArrayList<>();

    // Post:Scarb =1:N
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Scrab> postscrabs = new ArrayList<>();

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

    public void update(String postTitle, String postContent) {
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    public void setPostImagesNull(){
        this.postImgs = null;
    }

    public void increaseLikes() {
        this.postLike++;
    }

    public void decreaseLikes() {
        this.postLike--;
    }

    public void increaseScrabs() {
        this.postScrab++;
    }

    public void decreaseScarbs() {
        this.postScrab--;
    }

    public void increaseComments() {this.postcomment++;}

    public void decreaseComments() {this.postcomment--;}

    public void updateAnswered(){
        this.answered = Answered.Y;
    }

}

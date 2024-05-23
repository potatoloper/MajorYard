package com.KAU.majorYard.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Comment")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_no")
    private Long id; // comment_no
    //private String commentDepth;
    private String commentContent;
    private int depth;

    // Comment:Alert = 1:N
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alert> commentAlerts = new ArrayList<>();


    // Comment:Post = N:1
    @ManyToOne
    @JoinColumn(name = "post_no")
    private Post post;

    // Comment:Comment = 1:1
    // Comment가 부모 댓글을 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_pno")
    @JsonBackReference
    private Comment parentComment;

    // 한 댓글의 자식 댓글 리스트
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Comment> childComments = new ArrayList<>();

    // Comment : User = N:1
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    public void updateDepth(){
        this.depth = this.depth + 1;
    }

    public void update(String commentContent) {
        this.commentContent = commentContent;
    }
}

package entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Comment")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_no")
    private Long id; // comment_no

    private String comment_depth;

    private String comment_content;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime comment_created_dt;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime comment_modified_dt;



    // Comment:Alert = 1:N


    // Comment:Post = N:1
    @ManyToOne
    @JoinColumn(name = "post_no")
    private Post post;

    // Comment:Comment = 1:1
    // Comment가 부모 댓글을 참조
    @ManyToOne
    @JoinColumn(name = "comment_pno")
    private Comment parentComment;

    // 한 댓글의 자식 댓글 리스트
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> childComments = new ArrayList<>();


    // Comment : User = N:1
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;




}

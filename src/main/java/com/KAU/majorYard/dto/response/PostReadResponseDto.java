package com.KAU.majorYard.dto.response;

import com.KAU.majorYard.entity.Comment;
import com.KAU.majorYard.entity.Img;
import com.KAU.majorYard.entity.Post;
import com.KAU.majorYard.entity.majorYard_enum.Answered;
import com.KAU.majorYard.entity.majorYard_enum.PostType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PostReadResponseDto {

    private Long id;
    private String postTitle;
    private String postContent;
    private int postLike;
    private int postScrab;
    private int postcomment;

    @Enumerated(value = EnumType.STRING)
    private PostType postType;
    @Enumerated(value = EnumType.STRING)
    private Answered answered;

    private String nickName;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedTime;
    private List<Img> imgs;
    private List<Comment> postComments = new ArrayList<>();

    @Builder
    public PostReadResponseDto(Post post){
        this.id = post.getId();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.postLike = post.getPostLike();
        this.postScrab = post.getPostScrab();
        this.postcomment = post.getPostcomment();
        this.postType = post.getPostType();
        this.answered = post.getAnswered();
        this.nickName = post.getUser().getNickName();
        this.modifiedTime = post.getModifiedDate();
        this.imgs = post.getPostImgs();
        this.postComments = post.getPostComments();
    }

}

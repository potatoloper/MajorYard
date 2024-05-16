package com.KAU.majorYard.dto.response;

import com.KAU.majorYard.entity.*;
import com.KAU.majorYard.entity.majorYard_enum.Answered;
import com.KAU.majorYard.entity.majorYard_enum.PostType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class PostPagingResponseDto {

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

    // 게시판별 게시글 페이징 조회 response
    @Builder
    public PostPagingResponseDto(Post post){
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
    }

//    @Builder
//    public PostResponseDto(Long id, String postTitle, String postContent, Long postLike, Long postScrab, PostType postType, Answered answered, User user, Board board){
//        this.id = id;
//        this.postTitle = postTitle;
//        this.postContent = postContent;
//        this.postLike = postLike;
//        this.postScrab = postScrab;
//        this.postType = postType;
//        this.answered = answered;
//        this.user = user;
//        this.board = board;
//    }

//    public Post toEntity(){
//        return Post.builder()
//                .postTitle(this.postTitle)
//                .postContent(this.postContent)
//                .postType(this.postType)
//                .user(this.user)
//                .board(this.board)
//                .build();
//    }

}

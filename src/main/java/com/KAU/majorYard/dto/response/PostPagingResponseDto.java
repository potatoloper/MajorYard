package com.KAU.majorYard.dto.response;

import com.KAU.majorYard.entity.*;
import com.KAU.majorYard.entity.majorYard_enum.Answered;
import com.KAU.majorYard.entity.majorYard_enum.PostType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    //홍보게시판 특화
    @Enumerated(value = EnumType.STRING)
    private PostType postType;

    //질문게시판 특화
    @Enumerated(value = EnumType.STRING)
    private Answered answered;

    /* 스터디게시판 특화 */
    private String studyRegion;
    private Integer studyPartyOf;
    private Integer studyFee;
    private String studyUntil;

    //시사이슈게시판 특화
    private String contentUrl;

    private String nickName;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    private String modifiedTime;
    private List<String> imgUrls;

    // 게시판별 게시글 페이징 조회 response
    @Builder
    public PostPagingResponseDto(Post post, List<String> imgUrls){
        this.id = post.getId();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.postLike = post.getPostLike();
        this.postScrab = post.getPostScrab();
        this.postcomment = post.getPostcomment();
        this.postType = post.getPostType();
        this.answered = post.getAnswered();
        this.studyRegion = post.getStudyRegion();
        this.studyPartyOf = post.getStudyPartyOf();
        this.studyFee = post.getStudyFee();
        this.studyUntil = post.getStudyUntil();
        this.nickName = post.getUser().getNickName();
        this.modifiedTime = post.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.imgUrls = imgUrls;
        this.contentUrl = post.getUrl();
    }

}

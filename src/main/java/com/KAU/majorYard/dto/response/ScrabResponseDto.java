package com.KAU.majorYard.dto.response;

import com.KAU.majorYard.entity.Post;
import com.KAU.majorYard.entity.majorYard_enum.Answered;
import com.KAU.majorYard.entity.majorYard_enum.PostType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class ScrabResponseDto {
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
    private String modifiedTime;
    private List<String> imgUrls;

    @Builder
    public ScrabResponseDto(Post post, List<String> imgUrls){
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

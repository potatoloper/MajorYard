package com.KAU.majorYard.dto.response;

import com.KAU.majorYard.entity.Post;
import com.KAU.majorYard.entity.majorYard_enum.Answered;
import com.KAU.majorYard.entity.majorYard_enum.PostType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
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
    private String modifiedTime;
    private List<String> imgUrls;
    private String contentUrl;

    @Builder
    public PostReadResponseDto(Post post, List<String> imgUrls){
        this.id = post.getId();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.postLike = post.getPostLike();
        this.postScrab = post.getPostScrab();
        this.postcomment = post.getPostcomment();
        this.postType = post.getPostType();
        this.answered = post.getAnswered();
        this.nickName = post.getUser().getNickName();
        this.modifiedTime = post.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.imgUrls = imgUrls;
        this.contentUrl = post.getUrl();
    }

}

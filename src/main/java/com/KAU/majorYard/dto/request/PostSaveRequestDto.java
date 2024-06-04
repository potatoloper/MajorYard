package com.KAU.majorYard.dto.request;


import com.KAU.majorYard.entity.Board;
import com.KAU.majorYard.entity.Img;
import com.KAU.majorYard.entity.Post;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.entity.majorYard_enum.Answered;
import com.KAU.majorYard.entity.majorYard_enum.PostType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    @NotNull
    private String postTitle;
    @NotNull
    private String postContent;
    @NotNull
    @Column(name="user_no")
    private Long userNo;
    @NotNull
    private Long boardNo;
    @Enumerated(value = EnumType.STRING)
    private PostType postType;
    @Enumerated(value = EnumType.STRING)
    private Answered answered;

    private String studyRegion;
    private String studyUntil;
    private Integer studyFee;
    private Integer studyPartyOf;

    private String url;

//    @Builder
//    public Post toEntity(User user, Board board, List<Img> img){
//        return Post.builder()
//                .postTitle(this.postTitle)
//                .postContent(this.postContent)
//                .postType(this.postType)
//                .answered(this.answered)
//                .user(user)
//                .board(board)
//                .postImgs(img)
//                .studyRegion(this.studyRegion)
//                .studyFee(this.studyFee)
//                .studyPartyOf(this.studyPartyOf)
//                .studyUntil(this.studyUntil)
//                .build();
//    }

    @Builder
    public Post toEntity(User user, Board board){
        return Post.builder()
                .postTitle(this.postTitle)
                .postContent(this.postContent)
                .postType(this.postType)
                .answered(this.answered)
                .user(user)
                .board(board)
                .studyRegion(this.studyRegion)
                .studyFee(this.studyFee)
                .studyPartyOf(this.studyPartyOf)
                .studyUntil(this.studyUntil)
                .url(this.url)
                .build();
    }

}

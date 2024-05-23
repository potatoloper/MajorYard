package com.KAU.majorYard.dto.request;

import com.KAU.majorYard.entity.Comment;
import com.KAU.majorYard.entity.Post;
import com.KAU.majorYard.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveChildRequestDto {

    @NotNull
    private String commentContent;
    private Long userNo;
    private int depth;


    @Builder
    public Comment toEntity(User user, Post post, Comment comment){
        return Comment.builder()
                .commentContent(this.commentContent)
                .depth(this.depth)
                .user(user)
                .post(post)
                .parentComment(comment)
                .build();
    }
}

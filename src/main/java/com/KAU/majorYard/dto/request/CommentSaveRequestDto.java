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
public class CommentSaveRequestDto {

    @NotNull
    private String commentContent;
    private Long userNo;
    //private User user;
    //private Post post;

    @Builder
    public Comment toEntity(User user, Post post){
        return Comment.builder()
                .commentContent(this.commentContent)
                .user(user)
                .post(post)
                .build();
    }
}

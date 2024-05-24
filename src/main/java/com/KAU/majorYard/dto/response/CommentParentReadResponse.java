package com.KAU.majorYard.dto.response;

import com.KAU.majorYard.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class CommentParentReadResponse {
    private Long id;
    private String nickName;
    private String commentContent;
    private String modifiedTime;

    @Builder
    public CommentParentReadResponse(Comment comment){
        this.id = comment.getId();
        this.nickName = comment.getUser().getNickName();
        this.commentContent = comment.getCommentContent();
        this.modifiedTime = comment.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}

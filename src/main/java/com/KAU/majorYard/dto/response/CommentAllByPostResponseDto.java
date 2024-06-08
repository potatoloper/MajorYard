package com.KAU.majorYard.dto.response;

import com.KAU.majorYard.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@NoArgsConstructor
public class CommentAllByPostResponseDto {
    private Long id;
    private String nickName;
    private String commentContent;
    private String modifiedTime;
    private List<CommentAllByPostResponseDto> childComments;

    @Builder
    public CommentAllByPostResponseDto(Comment comment, List<CommentAllByPostResponseDto> childComments){
        this.id = comment.getId();
        this.nickName = comment.getUser().getNickName();
        this.commentContent = comment.getCommentContent();
        this.childComments = childComments;
        this.modifiedTime = comment.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Builder
    public CommentAllByPostResponseDto(Comment comment){
        this.id = comment.getId();
        this.nickName = comment.getUser().getNickName();
        this.commentContent = comment.getCommentContent();
        this.modifiedTime = comment.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}

package com.KAU.majorYard.dto.response;

import com.KAU.majorYard.entity.Alert;
import com.KAU.majorYard.entity.Comment;
import com.KAU.majorYard.entity.Post;
import com.KAU.majorYard.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CommentPagingResponseDto {
    private Long id;
    private String nickName;
    private String commentContent;
    private String modifiedTime;

    @Builder
    public CommentPagingResponseDto(Comment comment){
        this.id = comment.getId();
        this.nickName = comment.getUser().getNickName();
        this.commentContent = comment.getCommentContent();
        this.modifiedTime = comment.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}

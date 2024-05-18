package com.KAU.majorYard.dto.response;

import com.KAU.majorYard.entity.Department;
import com.KAU.majorYard.entity.Post;
import com.KAU.majorYard.entity.majorYard_enum.BoardName;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;
    private BoardName boardName;
    private List<Post> boardPosts = new ArrayList<>();
    private Department department;

    @Builder
    public BoardResponseDto(BoardName boardName, List<Post> boardPosts, Department department) {
        this.boardName = boardName;
        this.boardPosts = boardPosts;
        this.department = department;
    }
}

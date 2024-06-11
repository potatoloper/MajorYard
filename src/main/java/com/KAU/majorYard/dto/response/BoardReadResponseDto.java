package com.KAU.majorYard.dto.response;

import com.KAU.majorYard.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardReadResponseDto {
    private Long id;
    private String boardName;
    private String departmentName;

    public BoardReadResponseDto(Board board){
        this.id = board.getId();
        this.boardName = board.getBoardName().toString();
        this.departmentName = board.getDepartment().getDepartmentName();
    }
}

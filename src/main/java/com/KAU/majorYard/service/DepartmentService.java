package com.KAU.majorYard.service;

import com.KAU.majorYard.dto.response.BoardReadResponseDto;
import com.KAU.majorYard.dto.response.DepartmentResponseDto;
import com.KAU.majorYard.entity.Board;
import com.KAU.majorYard.entity.Department;
import com.KAU.majorYard.repository.BoardRepository;
import com.KAU.majorYard.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final BoardRepository boardRepository;

    // 모든 게시판 조회
    @Transactional(readOnly = true)
    public List<BoardReadResponseDto> readBoards(Long departNo){
        List<Board> boardList = boardRepository.findAllByDepartmentId(departNo);
        List<BoardReadResponseDto> response = new ArrayList<>();
        for (Board board : boardList){
            response.add(new BoardReadResponseDto(board));
        }
        return response;
    }

    // 모든 학과 조회
    @Transactional(readOnly = true)
    public List<DepartmentResponseDto> readAllDepartment(){
        List<Department> departmentList = departmentRepository.findAll();
        List<DepartmentResponseDto> response = new ArrayList<>();
        for (Department department : departmentList){
            response.add(new DepartmentResponseDto(department));
        }
        return response;
    }
}

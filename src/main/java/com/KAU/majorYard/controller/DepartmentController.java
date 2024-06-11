package com.KAU.majorYard.controller;

import com.KAU.majorYard.dto.CommonResponse;
import com.KAU.majorYard.dto.CommonRestResult;
import com.KAU.majorYard.dto.response.BoardReadResponseDto;
import com.KAU.majorYard.dto.response.DepartmentResponseDto;
import com.KAU.majorYard.entity.Board;
import com.KAU.majorYard.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // 활성화된 학과 리스트
    @GetMapping("departments")
    public CommonResponse getAllDepartments(){

        String resultMsg;
        String resultCode;

        try {
            List<DepartmentResponseDto> boards = departmentService.readAllDepartment();
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, boards);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }

    }

    // 각 학과별 게시판 PK, 이름 조회
    @GetMapping("{departmentNo}/boards")
    public CommonResponse getAllBoards(@PathVariable Long departmentNo){

        String resultMsg;
        String resultCode;

        try {
            List<BoardReadResponseDto> boards = departmentService.readBoards(departmentNo);
            resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
            return new CommonResponse(resultCode, resultMsg, boards);

        }catch (Exception e){
            resultCode = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getCode();
            resultMsg = CommonRestResult.CommonRestResultEnum.PASS_ERROR.getMessage();
            return new CommonResponse(resultCode, resultMsg);
        }

    }
}

package com.KAU.majorYard.service;

import com.KAU.majorYard.dto.request.BoardRequestDto;
import com.KAU.majorYard.dto.request.PostPagingRequestDto;
import com.KAU.majorYard.dto.request.PostUpdateRequestDto;
import com.KAU.majorYard.dto.response.PostPagingResponseDto;
import com.KAU.majorYard.dto.response.PostReadResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardService {
    public void saveboard(BoardRequestDto boardDto);

    void savePromotionPost(BoardRequestDto.promotionBoard postDto);
    void saveQuestionPost(BoardRequestDto.questionBoard postDto);
    void saveStudyPost(BoardRequestDto.studyBoard postDto);
    void saveFreePost(BoardRequestDto.freeBoard postDto);
    void saveIssuePost(BoardRequestDto.issueBoard postDto);


}

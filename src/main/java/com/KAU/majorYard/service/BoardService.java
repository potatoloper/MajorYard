package com.KAU.majorYard.service;

import com.KAU.majorYard.dto.request.BoardRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    void savePromotionPost(BoardRequestDto.promotionBoard postDto, List<MultipartFile> multipartFiles) throws IOException;
    void saveQuestionPost(BoardRequestDto.questionBoard postDto, List<MultipartFile> multipartFiles) throws IOException;
    void saveStudyPost(BoardRequestDto.studyBoard postDto, List<MultipartFile> multipartFiles) throws IOException;
    void saveFreePost(BoardRequestDto.freeBoard postDto, List<MultipartFile> multipartFiles) throws IOException;
    void saveIssuePost(BoardRequestDto.issueBoard postDto, List<MultipartFile> multipartFiles) throws IOException;
}
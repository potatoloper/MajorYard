package com.KAU.majorYard.service;

import com.KAU.majorYard.dto.request.BoardRequestDto;
import com.KAU.majorYard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Transactional
    @Override
    public void saveboard(BoardRequestDto boardDto) {
    }
}

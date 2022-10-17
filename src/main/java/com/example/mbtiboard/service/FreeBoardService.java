package com.example.mbtiboard.service;

import com.example.mbtiboard.dto.FreeBoardDTO;
import com.example.mbtiboard.entity.FreeBoard;
import com.example.mbtiboard.repository.FreeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FreeBoardService {

    private final FreeBoardRepository freeBoardRepository;

    @Transactional
    public void write (FreeBoard freeBoard) throws IOException {
        freeBoardRepository.save(freeBoard);
    }

    public Page<FreeBoard> list(Pageable pageable) {
        return freeBoardRepository.findAll(pageable);
    }

    public Page<FreeBoard> searchList(String searchKeyword, Pageable pageable) {
        return freeBoardRepository.findByBoardTitleContaining(searchKeyword, pageable);
    }

    public FreeBoard view(Long boardNo) {
        return freeBoardRepository.findById(boardNo)
                .orElseThrow(()->new RuntimeException("다시 입력해주세요."));
    }
    @Transactional
    public void deleteById(Long boardNo) {freeBoardRepository.deleteById(boardNo);}
}

package com.example.mbtiboard.service;

import com.example.mbtiboard.entity.EBoard;
import com.example.mbtiboard.repository.EBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EBoardService {

    private final EBoardRepository eBoardRepository;

    @Transactional
    public void write (EBoard eBoard) throws IOException {
        eBoardRepository.save(eBoard);
    }

    public Page<EBoard> list(Pageable pageable) { return eBoardRepository.findAll(pageable);}

    public Page<EBoard> searchList(String searchKeyword, Pageable pageable) {
        return eBoardRepository.findByBoardTitleContaining(searchKeyword, pageable);
    }

    public EBoard view(Long boardNo) {
        return eBoardRepository.findById(boardNo)
                .orElseThrow(()->new RuntimeException("다시 입력해주세요."));
    }
    @Transactional
    public void deleteById(Long boardNo) {eBoardRepository.deleteById(boardNo);}
}

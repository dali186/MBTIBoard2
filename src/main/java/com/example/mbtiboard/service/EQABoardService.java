package com.example.mbtiboard.service;

import com.example.mbtiboard.entity.EQABoard;
import com.example.mbtiboard.repository.EQABoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EQABoardService {

    private final EQABoardRepository eqaBoardRepository;

    @Transactional
    public void write (EQABoard eqaBoard) throws IOException {
        eqaBoardRepository.save(eqaBoard);
    }

    public Page<EQABoard> list(Pageable pageable) { return eqaBoardRepository.findAll(pageable);}

    public Page<EQABoard> searchList(String searchKeyword, Pageable pageable) {
        return eqaBoardRepository.findByBoardTitleContaining(searchKeyword, pageable);
    }

    public EQABoard view(Long boardNo) {
        return eqaBoardRepository.findById(boardNo)
                .orElseThrow(()->new RuntimeException("다시 입력해주세요."));
    }
    @Transactional
    public void deleteById(Long boardNo) {eqaBoardRepository.deleteById(boardNo);}
}

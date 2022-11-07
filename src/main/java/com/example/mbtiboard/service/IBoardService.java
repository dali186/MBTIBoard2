package com.example.mbtiboard.service;

import com.example.mbtiboard.entity.IBoard;
import com.example.mbtiboard.repository.IBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IBoardService {

    private final IBoardRepository iBoardRepository;

    @Transactional
    public void write (IBoard iBoard) throws IOException {
        iBoardRepository.save(iBoard);
    }

    public Page<IBoard> list(Pageable pageable) { return iBoardRepository.findAll(pageable);}

    public Page<IBoard> searchList(String searchKeyword, Pageable pageable) {
        return iBoardRepository.findByBoardTitleContaining(searchKeyword, pageable);
    }

    public IBoard view(Long boardNo) {
        return iBoardRepository.findById(boardNo)
                .orElseThrow(()->new RuntimeException("다시 입력해주세요."));
    }
    @Transactional
    public void deleteById(Long boardNo) {iBoardRepository.deleteById(boardNo);}
}

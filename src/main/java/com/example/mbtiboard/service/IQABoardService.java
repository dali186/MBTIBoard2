package com.example.mbtiboard.service;

import com.example.mbtiboard.entity.IQABoard;
import com.example.mbtiboard.repository.IQABoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IQABoardService {

    private final IQABoardRepository iqaBoardRepository;

    @Transactional
    public void write (IQABoard iqaBoard) throws IOException {
        iqaBoardRepository.save(iqaBoard);
    }

    public Page<IQABoard> list(Pageable pageable) { return iqaBoardRepository.findAll(pageable);}

    public Page<IQABoard> searchList(String searchKeyword, Pageable pageable) {
        return iqaBoardRepository.findByBoardTitleContaining(searchKeyword, pageable);
    }

    public IQABoard view(Long boardNo) {
        return iqaBoardRepository.findById(boardNo)
                .orElseThrow(()->new RuntimeException("다시 입력해주세요."));
    }
    @Transactional
    public void deleteById(Long boardNo) {iqaBoardRepository.deleteById(boardNo);}
}

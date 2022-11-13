package com.example.mbtiboard.service;

import com.example.mbtiboard.entity.RuleBoard;
import com.example.mbtiboard.repository.RuleBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RuleBoardService {

    private final RuleBoardRepository ruleBoardRepository;

    @Transactional
    public void write (RuleBoard ruleBoard) throws IOException {
        ruleBoardRepository.save(ruleBoard);
    }

    public Page<RuleBoard> list(Pageable pageable) { return ruleBoardRepository.findAll(pageable);}

    public Page<RuleBoard> searchList(String searchKeyword, Pageable pageable) {
        return ruleBoardRepository.findByBoardTitleContaining(searchKeyword, pageable);
    }

    public RuleBoard view(Long boardNo) {
        return ruleBoardRepository.findById(boardNo)
                .orElseThrow(()->new RuntimeException("다시 입력해주세요."));
    }
    @Transactional
    public void deleteById(Long boardNo) {ruleBoardRepository.deleteById(boardNo);}

    public List<RuleBoard> ruleList() {
        return ruleBoardRepository.findAll();
    }
}

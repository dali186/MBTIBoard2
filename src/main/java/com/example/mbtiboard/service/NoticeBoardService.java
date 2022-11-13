package com.example.mbtiboard.service;

import com.example.mbtiboard.entity.NoticeBoard;
import com.example.mbtiboard.repository.NoticeBoardRepository;
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
public class NoticeBoardService {

    private final NoticeBoardRepository noticeBoardRepository;

    @Transactional
    public void write (NoticeBoard noticeBoard) throws IOException {
        noticeBoardRepository.save(noticeBoard);
    }

    public Page<NoticeBoard> list(Pageable pageable) { return noticeBoardRepository.findAll(pageable);}

    public Page<NoticeBoard> searchList(String searchKeyword, Pageable pageable) {
        return noticeBoardRepository.findByBoardTitleContaining(searchKeyword, pageable);
    }

    public NoticeBoard view(Long boardNo) {
        return noticeBoardRepository.findById(boardNo)
                .orElseThrow(()->new RuntimeException("다시 입력해주세요."));
    }
    @Transactional
    public void deleteById(Long boardNo) {noticeBoardRepository.deleteById(boardNo);}

    public List<NoticeBoard> indexList() {
        return noticeBoardRepository.findAll();
    }
}

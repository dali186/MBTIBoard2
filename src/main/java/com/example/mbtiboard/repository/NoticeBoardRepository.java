package com.example.mbtiboard.repository;

import com.example.mbtiboard.entity.NoticeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {
    Page<NoticeBoard> findByBoardTitleContaining(String searchKeyword, Pageable pageable);
}

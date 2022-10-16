package com.example.mbtiboard.repository;

import com.example.mbtiboard.entity.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
    Page<FreeBoard> findByBoardTitleContaining(String searchKeyword, Pageable pageable);
}

package com.example.mbtiboard.repository;

import com.example.mbtiboard.entity.EBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EBoardRepository extends JpaRepository<EBoard, Long> {
    Page<EBoard> findByBoardTitleContaining(String searchKeyword, Pageable pageable);
}

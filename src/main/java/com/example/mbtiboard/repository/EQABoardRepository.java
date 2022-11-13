package com.example.mbtiboard.repository;

import com.example.mbtiboard.entity.EQABoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EQABoardRepository extends JpaRepository<EQABoard, Long> {
    Page<EQABoard> findByBoardTitleContaining(String searchKeyword, Pageable pageable);
}

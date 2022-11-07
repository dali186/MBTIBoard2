package com.example.mbtiboard.repository;

import com.example.mbtiboard.entity.IBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBoardRepository extends JpaRepository<IBoard, Long> {
    Page<IBoard> findByBoardTitleContaining(String searchKeyword, Pageable pageable);
}

package com.example.mbtiboard.repository;

import com.example.mbtiboard.entity.IQABoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQABoardRepository extends JpaRepository<IQABoard, Long> {
    Page<IQABoard> findByBoardTitleContaining(String searchKeyword, Pageable pageable);
}

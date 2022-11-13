package com.example.mbtiboard.repository;

import com.example.mbtiboard.entity.RuleBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleBoardRepository extends JpaRepository<RuleBoard, Long> {
    Page<RuleBoard> findByBoardTitleContaining(String searchKeyword, Pageable pageable);
}

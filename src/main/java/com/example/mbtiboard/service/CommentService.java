package com.example.mbtiboard.service;

import com.example.mbtiboard.dto.CommentDTO;
import com.example.mbtiboard.entity.Comment;
import com.example.mbtiboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Long writeComment(CommentDTO commentDTO) {
        Comment comment = commentDTO.toEntity();
        commentRepository.save(comment);
        return comment.getCommentNo();
    }

    public List<Comment> getCommentList(long boarNo, long boardType) {
        List<Comment> commentList = new ArrayList<>();
        commentList = commentRepository.findByBoardNoAndBoardType(boarNo, boardType);
        return commentList;
    }
}

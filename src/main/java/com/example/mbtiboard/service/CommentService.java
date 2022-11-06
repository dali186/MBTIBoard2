package com.example.mbtiboard.service;

import com.example.mbtiboard.dto.CommentDTO;
import com.example.mbtiboard.entity.Comment;
import com.example.mbtiboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}

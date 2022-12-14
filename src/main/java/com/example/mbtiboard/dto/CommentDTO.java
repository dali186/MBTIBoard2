package com.example.mbtiboard.dto;

import com.example.mbtiboard.entity.Comment;
import com.example.mbtiboard.entity.FreeBoard;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class CommentDTO {

    @Column(name = "comment_no")
    private Long commentNo;

    @Column(name = "board_no")
    private Long boardNo;

    @Column(name = "board_type")
    private Long boardType;

    @Column(name = "comment_author")
    private String commentAuthor;

    @Column(name = "comment_content")
    private String commentContent;

    @Builder
    public CommentDTO(long commentNo, long boardNo, long boardType,String commentAuthor, String commentContent) {
        this.commentNo = commentNo;
        this.boardNo = boardNo;
        this.boardType = boardType;
        this.commentAuthor = commentAuthor;
        this.commentContent = commentContent;
    }

    public Comment toEntity() {
        return Comment.builder()
                .boardNo(boardNo)
                .boardType(boardType)
                .commentAuthor(commentAuthor)
                .commentContent(commentContent)
                .build();
    }
}
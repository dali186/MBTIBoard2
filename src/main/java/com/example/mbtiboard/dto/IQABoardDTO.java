package com.example.mbtiboard.dto;

import com.example.mbtiboard.entity.IQABoard;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class IQABoardDTO {

    @Column(name = "board_no")
    private Long boardNo;

    @Column(name = "board_title")
    private String boardTitle;

    @Column(name = "board_content", length = 5000)
    private String boardContent;

    @Column(name = "board_author")
    private String boardAuthor;

    @Builder
    public IQABoardDTO(Long boardNo, String boardTitle, String boardContent, String boardAuthor) {
        this.boardNo = boardNo;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardAuthor = boardAuthor;
    }

    public IQABoard toEntity(){
        return IQABoard.builder()
                .boardNo(boardNo)
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardAuthor(boardAuthor)
                .build();
    }
}

package com.example.mbtiboard.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString
@Getter
@NoArgsConstructor
public class FreeBoard extends BaseTime{

    @Id
    @Column(name = "board_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    @Column(name = "board_title")
    private String boardTitle;

    @Column(name = "board_content")
    private String boardContent;

    @Column(name = "board_author")
    private String boardAuthor;

    @Builder
    public FreeBoard(Long boardNo, String boardTitle, String boardContent, String boardAuthor) {
        this.boardNo = boardNo;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardAuthor = boardAuthor;
    }
}

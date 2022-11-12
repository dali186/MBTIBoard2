package com.example.mbtiboard.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString
@Getter
@NoArgsConstructor
public class IBoard extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no")
    private Long boardNo;

    @Column(name = "board_title")
    private String boardTitle;

    @Column(name = "board_type")
    private String boardType;

    @Column(name = "board_content", length = 5000)
    private String boardContent;

    @Column(name = "board_author")
    private String boardAuthor;

    @Builder
    public IBoard(Long boardNo, String boardTitle, String boardType,String boardContent, String boardAuthor) {
        this.boardNo = boardNo;
        this.boardTitle = boardTitle;
        this.boardType = boardType;
        this.boardContent = boardContent;
        this.boardAuthor = boardAuthor;
    }
}

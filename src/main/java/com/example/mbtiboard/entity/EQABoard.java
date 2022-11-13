package com.example.mbtiboard.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString
@Getter
@NoArgsConstructor
public class EQABoard extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no")
    private Long boardNo;

    @Column(name = "board_title")
    private String boardTitle;

    @Column(name = "board_content", length = 5000)
    private String boardContent;

    @Column(name = "board_author")
    private String boardAuthor;

    @Builder
    public EQABoard(Long boardNo, String boardTitle, String boardContent, String boardAuthor) {
        this.boardNo = boardNo;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardAuthor = boardAuthor;
    }
}

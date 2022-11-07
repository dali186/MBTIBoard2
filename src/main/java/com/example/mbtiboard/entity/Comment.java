package com.example.mbtiboard.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Comment extends BaseTime{

    @Id
    @Column(name = "comment_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNo;
    @Column(name = "board_no")
    private Long boardNo;

    //Freeboard = 0 , Iboard = 1
    @Column(name = "board_type")
    private Long boardType;

    @Column(name = "comment_author")
    private String commentAuthor;

    @Column(name = "comment_content")
    private String commentContent;

    @Builder
    public Comment(long commentNo, long boardNo, long boardType,String commentAuthor, String commentContent) {
        this.commentNo = commentNo;
        this.boardNo = boardNo;
        this.boardType = boardType;
        this.commentAuthor = commentAuthor;
        this.commentContent = commentContent;
    }
}

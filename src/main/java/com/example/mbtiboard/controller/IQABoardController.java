package com.example.mbtiboard.controller;

import com.example.mbtiboard.dto.CommentDTO;
import com.example.mbtiboard.entity.Comment;
import com.example.mbtiboard.entity.IQABoard;
import com.example.mbtiboard.service.CommentService;
import com.example.mbtiboard.service.IQABoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IQABoardController {
    private final IQABoardService iqaBoardService;
    private final CommentService commentService;

    @GetMapping("board/iqawrite")
    public String IQAWrite() { return "iqaboard/write";}

    @PostMapping("board/iqawrite/action")
    public String iqaBoardWriteAction(Model model, IQABoard iqaBoard) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails)principal).getUsername();
        iqaBoard.setBoardAuthor(userId);
        iqaBoardService.write(iqaBoard);

        model.addAttribute("message", "게시글이 등록되었습니다.");
        model.addAttribute("iqasearchUrl", "/board/iqalist");

        return "iqaboard/iqawritems";
    }

    @GetMapping("board/iqalist")
    public String iList(Model model, @PageableDefault(page = 0, size = 10, sort = "boardNo", direction = Sort.Direction.DESC)Pageable pageable, String searchKeyword) {
        Page<IQABoard> list = null;

        if(searchKeyword == null) {
            list = iqaBoardService.list(pageable);
        } else  {
            list = iqaBoardService.searchList(searchKeyword,pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "iqaboard/list";
    }

    @GetMapping("board/iqaview/{boardNo}")
    public String freeView(Model model, @PathVariable("boardNo") Long boardNo) {
        model.addAttribute("IQABoard", iqaBoardService.view(boardNo));
        model.addAttribute("commentDTO", new CommentDTO());
        List<Comment> commentList = commentService.getCommentList(boardNo, 2);
        model.addAttribute("commentList", commentList);
        return "iqaboard/view";
    }

    @GetMapping("board/iqadel/{boardNo}")
    public String freeDel(@PathVariable("boardNo") Long boardNo) {
        iqaBoardService.deleteById(boardNo);

        return "redirect:board/iqalist";
    }

    @GetMapping("board/iqamodify/{boardNo}")
    public String freeMod(@PathVariable("boardNo") Long boardNo, Model model) {
        model.addAttribute("IQAboard", iqaBoardService.view(boardNo));

        return "iqaboard/modify";
    }

    @PostMapping("board/iqaupdate/{boardNo}")
    public String freeUpdate(@PathVariable("boardNo") Long boardNo, IQABoard iqaBoard) throws IOException{
        IQABoard iqaBoardTemp = iqaBoardService.view(boardNo);
        iqaBoardTemp.setBoardTitle(iqaBoard.getBoardTitle());
        iqaBoardTemp.setBoardContent(iqaBoard.getBoardContent());

        iqaBoardService.write(iqaBoardTemp);

        return "redirect:board/iqalist";
    }
    @PostMapping("iqacomment/save")
    public String writeComment(CommentDTO commentDTO) {
        commentService.writeComment(commentDTO);
        return "redirect:/";
    }
}

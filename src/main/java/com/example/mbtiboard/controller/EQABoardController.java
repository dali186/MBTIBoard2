package com.example.mbtiboard.controller;

import com.example.mbtiboard.dto.CommentDTO;
import com.example.mbtiboard.entity.Comment;
import com.example.mbtiboard.entity.EQABoard;
import com.example.mbtiboard.service.CommentService;
import com.example.mbtiboard.service.EQABoardService;
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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EQABoardController {
    private final EQABoardService eqaBoardService;
    private final CommentService commentService;
    private final HttpServletRequest request;

    @GetMapping("board/eqawrite")
    public String EQAWrite() { return "eqaboard/write";}

    @PostMapping("board/eqawrite/action")
    public String eqaBoardWriteAction(Model model, EQABoard eqaBoard) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails)principal).getUsername();
        eqaBoard.setBoardAuthor(userId);
        eqaBoardService.write(eqaBoard);

        model.addAttribute("message", "게시글이 등록되었습니다.");
        model.addAttribute("eqasearchUrl", "/board/eqalist");

        return "eqaboard/eqawritems";
    }

    @GetMapping("board/eqalist")
    public String eqaList(Model model, @PageableDefault(page = 0, size = 10, sort = "boardNo", direction = Sort.Direction.DESC)Pageable pageable, String searchKeyword) {
        Page<EQABoard> list = null;

        if(searchKeyword == null) {
            list = eqaBoardService.list(pageable);
        } else  {
            list = eqaBoardService.searchList(searchKeyword,pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "eqaboard/list";
    }

    @GetMapping("board/eqaview/{boardNo}")
    public String freeView(Model model, @PathVariable("boardNo") Long boardNo) {
        model.addAttribute("EQABoard", eqaBoardService.view(boardNo));
        model.addAttribute("commentDTO", new CommentDTO());
        List<Comment> commentList = commentService.getCommentList(boardNo, 4);
        model.addAttribute("commentList", commentList);
        return "eqaboard/view";
    }

    @GetMapping("board/eqadel/{boardNo}")
    public String freeDel(@PathVariable("boardNo") Long boardNo) {
        eqaBoardService.deleteById(boardNo);

        return "redirect:board/eqalist";
    }

    @GetMapping("board/eqamodify/{boardNo}")
    public String freeMod(@PathVariable("boardNo") Long boardNo, Model model) {
        model.addAttribute("EQAboard", eqaBoardService.view(boardNo));

        return "eqaboard/modify";
    }

    @PostMapping("board/eqaupdate/{boardNo}")
    public String freeUpdate(@PathVariable("boardNo") Long boardNo, EQABoard eqaBoard) throws IOException{
        EQABoard eqaBoardTemp = eqaBoardService.view(boardNo);
        eqaBoardTemp.setBoardTitle(eqaBoard.getBoardTitle());
        eqaBoardTemp.setBoardContent(eqaBoard.getBoardContent());

        eqaBoardService.write(eqaBoardTemp);

        return "redirect:board/eqalist";
    }
    @PostMapping("eqacomment/save")
    public String writeComment(CommentDTO commentDTO) {
        commentService.writeComment(commentDTO);
        String referer = request.getHeader("referer");
        return "redirect:" + referer;
    }
}

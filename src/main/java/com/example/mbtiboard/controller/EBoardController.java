package com.example.mbtiboard.controller;

import com.example.mbtiboard.dto.CommentDTO;
import com.example.mbtiboard.entity.Comment;
import com.example.mbtiboard.entity.EBoard;
import com.example.mbtiboard.service.CommentService;
import com.example.mbtiboard.service.EBoardService;
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
public class EBoardController {
    private final EBoardService eBoardService;
    private final CommentService commentService;
    private final HttpServletRequest request;

    @GetMapping("board/ewrite")
    public String EWrite() { return "eboard/write";}

    @PostMapping("board/ewrite/action")
    public String eBoardWriteAction(Model model, EBoard eBoard) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails)principal).getUsername();
        eBoard.setBoardAuthor(userId);
        eBoardService.write(eBoard);

        model.addAttribute("message", "게시글이 등록되었습니다.");
        model.addAttribute("esearchUrl", "/board/elist");

        return "eboard/ewritems";
    }

    @GetMapping("board/elist")
    public String eList(Model model, @PageableDefault(page = 0, size = 10, sort = "boardNo", direction = Sort.Direction.DESC)Pageable pageable, String searchKeyword) {
        Page<EBoard> list = null;

        if(searchKeyword == null) {
            list = eBoardService.list(pageable);
        } else  {
            list = eBoardService.searchList(searchKeyword,pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "eboard/list";
    }

    @GetMapping("board/eview/{boardNo}")
    public String freeView(Model model, @PathVariable("boardNo") Long boardNo) {
        model.addAttribute("EBoard", eBoardService.view(boardNo));
        model.addAttribute("commentDTO", new CommentDTO());
        List<Comment> commentList = commentService.getCommentList(boardNo, 3);
        model.addAttribute("commentList", commentList);
        return "eboard/view";
    }

    @GetMapping("board/edel/{boardNo}")
    public String freeDel(@PathVariable("boardNo") Long boardNo) {
        eBoardService.deleteById(boardNo);

        return "redirect:board/elist";
    }

    @GetMapping("board/emodify/{boardNo}")
    public String freeMod(@PathVariable("boardNo") Long boardNo, Model model) {
        model.addAttribute("Eboard", eBoardService.view(boardNo));

        return "eboard/modify";
    }

    @PostMapping("board/eupdate/{boardNo}")
    public String freeUpdate(@PathVariable("boardNo") Long boardNo, EBoard eBoard) throws IOException{
        EBoard eBoardTemp = eBoardService.view(boardNo);
        eBoardTemp.setBoardTitle(eBoard.getBoardTitle());
        eBoardTemp.setBoardContent(eBoard.getBoardContent());

        eBoardService.write(eBoardTemp);

        return "redirect:board/elist";
    }
    @PostMapping("ecomment/save")
    public String writeComment(CommentDTO commentDTO) {
        commentService.writeComment(commentDTO);
        String referer = request.getHeader("referer");
        return "redirect:" + referer;
    }
}

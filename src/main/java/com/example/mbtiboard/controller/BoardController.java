package com.example.mbtiboard.controller;

import com.example.mbtiboard.dto.CommentDTO;
import com.example.mbtiboard.entity.Comment;
import com.example.mbtiboard.entity.FreeBoard;
import com.example.mbtiboard.repository.CommentRepository;
import com.example.mbtiboard.service.CommentService;
import com.example.mbtiboard.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final FreeBoardService freeBoardService;
    private final CommentService commentService;

    @GetMapping("board/freewrite")
    public String freeWrite() {
        return "board/write";
    }

    @PostMapping("board/freewrite/action")
    public String freeBoardWriteAction(Model model, FreeBoard freeBoard) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails)principal).getUsername();
        freeBoard.setBoardAuthor(userId);
        freeBoardService.write(freeBoard);

        model.addAttribute("message", "게시글이 등록되었습니다.");
        model.addAttribute("searchUrl", "/board/freelist");

        return "board/freewritems";
    }

    @GetMapping("board/freelist")
    public String freeList(Model model, @PageableDefault(page = 0, size = 10, sort = "boardNo", direction = Sort.Direction.DESC)Pageable pageable, String searchKeyword) {
        Page<FreeBoard> list = null;

        if(searchKeyword == null) {
            list = freeBoardService.list(pageable);
        } else {
            list = freeBoardService.searchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board/list";
    }

    @GetMapping("board/freeview/{boardNo}")
    public String freeView(Model model, @PathVariable("boardNo") Long boardNo) {
        model.addAttribute("FreeBoard", freeBoardService.view(boardNo));
        model.addAttribute("commentDTO", new CommentDTO());
        List<Comment> commentList = commentService.getCommentList(boardNo, 0);
        model.addAttribute("commentList", commentList);
        return "board/view";
    }

    @GetMapping("board/freedel/{boardNo}")
    public String freeDel(@PathVariable("boardNo") Long boardNo) {
        freeBoardService.deleteById(boardNo);

        return "redirect:/board/freelist";
    }

    @GetMapping("board/modify/{boardNo}")
    public String freeMod(@PathVariable("boardNo") Long boardNo, Model model) {
        model.addAttribute("Freeboard", freeBoardService.view(boardNo));

        return "board/modify";
    }

    @PostMapping("board/update/{boardNo}")
    public String freeUpdate(@PathVariable("boardNo") Long boardNo, FreeBoard freeBoard) throws IOException{
        FreeBoard freeBoardTemp = freeBoardService.view(boardNo);
        freeBoardTemp.setBoardTitle(freeBoard.getBoardTitle());
        freeBoardTemp.setBoardContent(freeBoard.getBoardContent());

        freeBoardService.write(freeBoardTemp);

        return "redirect:/board/freelist";
    }
    @PostMapping("comment/save")
    public String writeComment(CommentDTO commentDTO) {
        commentService.writeComment(commentDTO);
        return "redirect:/";
    }
}

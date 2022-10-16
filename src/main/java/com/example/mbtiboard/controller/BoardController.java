package com.example.mbtiboard.controller;

import com.example.mbtiboard.dto.FreeBoardDTO;
import com.example.mbtiboard.entity.FreeBoard;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final FreeBoardService freeBoardService;

    @GetMapping("/board/freewrite")
    public String freeWrite(Model model, FreeBoardDTO freeBoardDTO) {
        model.addAttribute("freeBoardDTO", freeBoardDTO);
        return "/board/write";
    }

    @PostMapping("/board/freewrite/action")
    public String freeBoardWriteAction(Model model, FreeBoardDTO freeBoardDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails)principal).getUsername();
        freeBoardDTO.setBoardAuthor(userId);
        freeBoardService.createFreeBoard(freeBoardDTO);
        return "index";
    }

    @GetMapping("/board/freelist")
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

        return "/board/list";
    }

    @GetMapping("/board/freeview/{boardNo}")
    public String freeView(Model model, @PathVariable("boardNo") Long boardNo) {
        model.addAttribute("FreeBoard", freeBoardService.view(boardNo));
        return "board/view";
    }
}

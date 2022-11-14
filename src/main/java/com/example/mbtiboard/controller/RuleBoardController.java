package com.example.mbtiboard.controller;

import com.example.mbtiboard.entity.RuleBoard;
import com.example.mbtiboard.service.RuleBoardService;
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

@Controller
@RequiredArgsConstructor
public class RuleBoardController {
    private final RuleBoardService ruleBoardService;

    @GetMapping("board/rulewrite")
    public String RuleWrite() { return "ruleboard/write";}

    @PostMapping("board/rulewrite/action")
    public String ruleBoardWriteAction(Model model, RuleBoard ruleBoard) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails)principal).getUsername();
        ruleBoard.setBoardAuthor(userId);
        ruleBoardService.write(ruleBoard);

        model.addAttribute("message", "게시글이 등록되었습니다.");
        model.addAttribute("rulesearchUrl", "/board/rulelist");

        return "ruleboard/rulewritems";
    }

    @GetMapping("board/rulelist")
    public String ruleList(Model model, @PageableDefault(page = 0, size = 10, sort = "boardNo", direction = Sort.Direction.DESC)Pageable pageable, String searchKeyword) {
        Page<RuleBoard> list = null;

        if(searchKeyword == null) {
            list = ruleBoardService.list(pageable);
        } else  {
            list = ruleBoardService.searchList(searchKeyword,pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "ruleboard/list";
    }

    @GetMapping("board/ruleview/{boardNo}")
    public String freeView(Model model, @PathVariable("boardNo") Long boardNo) {
        model.addAttribute("RuleBoard", ruleBoardService.view(boardNo));
        return "ruleboard/view";
    }

    @GetMapping("board/ruledel/{boardNo}")
    public String freeDel(@PathVariable("boardNo") Long boardNo) {
        ruleBoardService.deleteById(boardNo);

        return "redirect:board/rulelist";
    }

    @GetMapping("board/rulemodify/{boardNo}")
    public String freeMod(@PathVariable("boardNo") Long boardNo, Model model) {
        model.addAttribute("Ruleboard", ruleBoardService.view(boardNo));

        return "ruleboard/rulemodify";
    }

    @PostMapping("board/ruleupdate/{boardNo}")
    public String freeUpdate(@PathVariable("boardNo") Long boardNo, RuleBoard ruleBoard) throws IOException{
        RuleBoard ruleBoardTemp = ruleBoardService.view(boardNo);
        ruleBoardTemp.setBoardTitle(ruleBoard.getBoardTitle());
        ruleBoardTemp.setBoardContent(ruleBoard.getBoardContent());

        ruleBoardService.write(ruleBoardTemp);

        return "redirect:board/rulelist";
    }
}

package com.example.mbtiboard.board.controller;

import com.example.mbtiboard.board.dto.BoardRequestDTO;
import com.example.mbtiboard.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public String getBoardListPage(Model model
            , @RequestParam(required = false, defaultValue = "0") Integer page
            , @RequestParam(required = false, defaultValue = "5") Integer size) throws Exception {

        try {
            model.addAttribute("resultMap", boardService.findAll(page, size));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return "freelist";
    }

    @GetMapping("/board/write")
    public String getBoardWritePage(Model model, BoardRequestDTO boardRequestDto) {
        return "/board/write";
    }

    @GetMapping("/board/view")
    public String getBoardViewPage(Model model, BoardRequestDTO boardRequestDTO) throws Exception {

        try {
            if (boardRequestDTO.getBoardNo() != null) {
                model.addAttribute("info", boardService.findByNo(boardRequestDTO.getBoardNo()));
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return "/board/view";
    }

    @PostMapping("/board/write/action")
    public String boardWriteAction(Model model, BoardRequestDTO boardRequestDTO) throws Exception {

        try {
            Long result = boardService.save(boardRequestDTO);

            if (result < 0) {
                throw new Exception("#Exception boardWriteAction!");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return "redirect:/board/list";
    }
}

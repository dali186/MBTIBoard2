package com.example.mbtiboard.controller;

import com.example.mbtiboard.dto.CommentDTO;
import com.example.mbtiboard.entity.Comment;
import com.example.mbtiboard.entity.FreeBoard;
import com.example.mbtiboard.entity.IBoard;
import com.example.mbtiboard.service.CommentService;
import com.example.mbtiboard.service.IBoardService;
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
public class IBoardController {
    private final IBoardService iBoardService;
    private final CommentService commentService;

    @GetMapping("board/iwrite")
    public String IWrite() { return "iboard/write";}

    @PostMapping("board/iwrite/action")
    public String iBoardWriteAction(Model model, IBoard iBoard) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails)principal).getUsername();
        iBoard.setBoardAuthor(userId);
        iBoardService.write(iBoard);

        model.addAttribute("message", "게시글이 등록되었습니다.");
        model.addAttribute("isearchUrl", "/board/ilist");

        return "iboard/iwritems";
    }

    @GetMapping("board/ilist")
    public String iList(Model model, @PageableDefault(page = 0, size = 10, sort = "boardNo", direction = Sort.Direction.DESC)Pageable pageable, String searchKeyword) {
        Page<IBoard> list = null;

        if(searchKeyword == null) {
            list = iBoardService.list(pageable);
        } else  {
            list = iBoardService.searchList(searchKeyword,pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "iboard/list";
    }

    @GetMapping("board/iview/{boardNo}")
    public String freeView(Model model, @PathVariable("boardNo") Long boardNo) {
        model.addAttribute("IBoard", iBoardService.view(boardNo));
        model.addAttribute("commentDTO", new CommentDTO());
        List<Comment> commentList = commentService.getCommentList(boardNo, 1);
        model.addAttribute("commentList", commentList);
        return "iboard/view";
    }

    @GetMapping("board/idel/{boardNo}")
    public String freeDel(@PathVariable("boardNo") Long boardNo) {
        iBoardService.deleteById(boardNo);

        return "redirect:/board/ilist";
    }

    @GetMapping("board/imodify/{boardNo}")
    public String freeMod(@PathVariable("boardNo") Long boardNo, Model model) {
        model.addAttribute("IBoard", iBoardService.view(boardNo));

        return "iboard/imodify";
    }

    @PostMapping("board/iupdate/{boardNo}")
    public String freeUpdate(@PathVariable("boardNo") Long boardNo, IBoard iBoard) throws IOException{
        IBoard iBoardTemp = iBoardService.view(boardNo);
        iBoardTemp.setBoardTitle(iBoard.getBoardTitle());
        iBoardTemp.setBoardContent(iBoard.getBoardContent());

        iBoardService.write(iBoardTemp);

        return "redirect:/board/ilist";
    }
    @PostMapping("icomment/save")
    public String writeComment(CommentDTO commentDTO) {
        commentService.writeComment(commentDTO);
        return "redirect:/";
    }
}

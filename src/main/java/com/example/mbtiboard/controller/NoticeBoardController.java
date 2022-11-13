package com.example.mbtiboard.controller;

import com.example.mbtiboard.dto.CommentDTO;
import com.example.mbtiboard.entity.Comment;
import com.example.mbtiboard.entity.NoticeBoard;
import com.example.mbtiboard.service.CommentService;
import com.example.mbtiboard.service.NoticeBoardService;
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
public class NoticeBoardController {
    private final NoticeBoardService noticeBoardService;
    private final CommentService commentService;

    @GetMapping("board/noticewrite")
    public String NoticeWrite() { return "noticeboard/write";}

    @PostMapping("board/noticewrite/action")
    public String noticeBoardWriteAction(Model model, NoticeBoard noticeBoard) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails)principal).getUsername();
        noticeBoard.setBoardAuthor(userId);
        noticeBoardService.write(noticeBoard);

        model.addAttribute("message", "게시글이 등록되었습니다.");
        model.addAttribute("noticesearchUrl", "/board/noticelist");

        return "noticeboard/noticewritems";
    }

    @GetMapping("board/noticelist")
    public String noticeList(Model model, @PageableDefault(page = 0, size = 10, sort = "boardNo", direction = Sort.Direction.DESC)Pageable pageable, String searchKeyword) {
        Page<NoticeBoard> list = null;

        if(searchKeyword == null) {
            list = noticeBoardService.list(pageable);
        } else  {
            list = noticeBoardService.searchList(searchKeyword,pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "noticeboard/list";
    }

    @GetMapping("board/noticeview/{boardNo}")
    public String freeView(Model model, @PathVariable("boardNo") Long boardNo) {
        model.addAttribute("NoticeBoard", noticeBoardService.view(boardNo));
        model.addAttribute("commentDTO", new CommentDTO());
        List<Comment> commentList = commentService.getCommentList(boardNo, 5);
        model.addAttribute("commentList", commentList);
        return "noticeboard/view";
    }

    @GetMapping("board/noticedel/{boardNo}")
    public String freeDel(@PathVariable("boardNo") Long boardNo) {
        noticeBoardService.deleteById(boardNo);

        return "redirect:board/noticelist";
    }

    @GetMapping("board/noticemodify/{boardNo}")
    public String freeMod(@PathVariable("boardNo") Long boardNo, Model model) {
        model.addAttribute("Noticeboard", noticeBoardService.view(boardNo));

        return "noticeboard/noticemodify";
    }

    @PostMapping("board/noticeupdate/{boardNo}")
    public String freeUpdate(@PathVariable("boardNo") Long boardNo, NoticeBoard noticeBoard) throws IOException{
        NoticeBoard noticeBoardTemp = noticeBoardService.view(boardNo);
        noticeBoardTemp.setBoardTitle(noticeBoard.getBoardTitle());
        noticeBoardTemp.setBoardContent(noticeBoard.getBoardContent());

        noticeBoardService.write(noticeBoardTemp);

        return "redirect:/board/noticelist";
    }
    @PostMapping("noticecomment/save")
    public String writeComment(CommentDTO commentDTO) {
        commentService.writeComment(commentDTO);
        return "redirect:/";
    }
}

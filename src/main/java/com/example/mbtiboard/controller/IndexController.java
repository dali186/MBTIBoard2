package com.example.mbtiboard.controller;

import com.example.mbtiboard.config.auth.PrincipalDetails;
import com.example.mbtiboard.dto.AccountDTO;
import com.example.mbtiboard.entity.Account;
import com.example.mbtiboard.entity.NoticeBoard;
import com.example.mbtiboard.entity.RuleBoard;
import com.example.mbtiboard.repository.AccountRepository;
import com.example.mbtiboard.service.AccountService;
import com.example.mbtiboard.service.NoticeBoardService;
import com.example.mbtiboard.service.RuleBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class IndexController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final NoticeBoardService noticeBoardService;
    private final RuleBoardService ruleBoardService;

    @GetMapping({"","/"})
    public String index(Model model) {
        List<NoticeBoard> noticeList = noticeBoardService.indexList();
        List<RuleBoard> ruleList = ruleBoardService.ruleList();
        model.addAttribute("noticeList", noticeList);
        model.addAttribute("ruleList", ruleList);
        return "index";
    }
    @GetMapping("loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("joinForm")
    public String join(Model model) {
        model.addAttribute("accountDTO", new AccountDTO());
        return "joinForm";
    }

    @PostMapping("joinForm")
    public String acceptJoin(@Validated AccountDTO accountDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "joinForm";
        }
        accountService.createAccount(accountDTO);
        return "redirect:loginForm";
    }

    @GetMapping("modForm")
    public String updateJoin(Principal principal, Model model) {
        String userId = principal.getName();
        Account account = accountRepository.findByUserEmail(userId);
        model.addAttribute("accountDTO", account);
        return "modForm";
    }

    @PostMapping("modForm")
    public String acceptMod(@Validated AccountDTO accountDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "modForm";
        }
        accountService.updateAccount(accountDTO);
        return "index";
    }
    @GetMapping("mbtitest")
    public String mbtiTest() {
        return "mbtitest";
    }

}

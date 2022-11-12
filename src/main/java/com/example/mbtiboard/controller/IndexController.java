package com.example.mbtiboard.controller;

import com.example.mbtiboard.config.auth.PrincipalDetails;
import com.example.mbtiboard.dto.AccountDTO;
import com.example.mbtiboard.entity.Account;
import com.example.mbtiboard.repository.AccountRepository;
import com.example.mbtiboard.service.AccountService;
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


@Controller
@RequiredArgsConstructor
public class IndexController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @GetMapping({"","/"})
    public String index() {
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
    public String mbtiTest(){
        return "mbtitest";
    }
    @GetMapping("mbtitestresult")
    public String mbtiTestResult(){

        return "mbtitestresult";
    }
}

package com.example.mbtiboard.service;

import com.example.mbtiboard.repository.AccountRepository;
import com.example.mbtiboard.dto.AccountDTO;
import com.example.mbtiboard.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public Long createAccount(AccountDTO accountDTO) {
        Account account = accountDTO.toEntity();
        account.setUserRole("ROLE_USER");   //user로 등록
        accountRepository.save(account);
        return account.getUserNo();
    }

    @Transactional
    public Long updateAccount(AccountDTO accountDTO) {
        Account account = accountDTO.toEntities();
        account.setUserRole("ROLE_USER");
        accountRepository.save(account);
        return account.getUserNo();
    }
}

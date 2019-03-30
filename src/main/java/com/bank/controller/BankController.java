package com.bank.controller;

import com.bank.account.Account;
import com.bank.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class BankController {

    @Autowired
    AccountService accountServiceImpl;

    public String withDraw(@RequestParam("accountId") String accountId,
                           @RequestParam("amount")BigDecimal amount) throws Exception {
        return accountServiceImpl.withdraw(accountId, amount);
    }

    public String deposit(@RequestParam("accountId") String accountId,
                         @RequestParam("amount")BigDecimal amount) throws Exception {
        return accountServiceImpl.deposit(accountId, amount);
    }

    public String transfer(@RequestParam("fromAccountId") String fromAccountId,
                         @RequestParam("toAccountId") String toAccountId,
                        @RequestParam("amount")BigDecimal amount) throws Exception {
        return accountServiceImpl.transfer(fromAccountId, toAccountId, amount);
    }

    public BigDecimal getBalance(@RequestParam("accountId") String accountId) throws Exception {
        return accountServiceImpl.getBalance(accountId);
    }

}

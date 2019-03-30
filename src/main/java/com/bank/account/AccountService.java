package com.bank.account;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

public interface AccountService {

    String WITHDRAW = "WITHDRAW";

    String DEPOSIT = "DEPOSIT";

    String transfer(String from, String to, BigDecimal amountToTransfer) throws Exception;

    String deposit(String to, BigDecimal amountToDeplosit) throws Exception;

    String withdraw(String from, BigDecimal amountToWithdraw)throws Exception;

    BigDecimal getBalance(String acc)throws Exception;

    String getStatement(String accountId, String fromDate, String toDate)throws Exception;
}

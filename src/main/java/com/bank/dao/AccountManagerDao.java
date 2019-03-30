package com.bank.dao;

import com.bank.account.Account;
import com.bank.account.TransactionDetails;

import java.text.ParseException;
import java.util.List;


public interface AccountManagerDao {

    void storeDetails(Account accountId, TransactionDetails transactionDetails);

    List<String> getStatement(String accountId, String fromDate, String toDate) throws ParseException;

    Account getAccount(Account account);
}

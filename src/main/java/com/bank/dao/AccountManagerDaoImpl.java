package com.bank.dao;

import com.bank.account.Account;
import com.bank.account.TransactionDetails;
import org.springframework.expression.ExpressionException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


public enum AccountManagerDaoImpl implements AccountManagerDao{

    INSTANCE;

    private Map<Account, List<TransactionDetails>> accountDetails = new ConcurrentHashMap<>();

//    private AccountManagerDao accountManagerDao;
//    public static synchronized  AccountManagerDao getInstance(){
//        if
//    }


    public void storeDetails(Account accountId, TransactionDetails transactionDetails){
        accountDetails
                .computeIfAbsent(accountId, acc -> new ArrayList<>()).add(transactionDetails);
    }

    public List<String> getStatement(String accountId, String fromDate, String toDate) throws ParseException {
        List<String> transactionDetailsStr = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date frmDate = dateFormat.parse(fromDate);
        Date tDate = dateFormat.parse(toDate);
        if(accountDetails.containsKey(accountId)){
            transactionDetailsStr.addAll(accountDetails.get(accountId)
                     .stream()
                     .filter(td -> {
                         try {
                             Date tranDate = dateFormat.parse(td.getTransactionDate());
                             if(tranDate.after(frmDate) && tranDate.before(tDate))
                                 return true;
                         } catch (ParseException e) {
                             e.printStackTrace();
                         }
                         return false;
                     })
                     .map(t -> t.toString())
                     .collect(Collectors.toList()));
        }
        return  transactionDetailsStr;
    }

    public Account getAccount(Account account){
        Optional<Account> accountFrmStore = accountDetails
                .entrySet()
                .stream()
                .filter(acc -> acc.getKey().equals(account))
                .map(a -> a.getKey())
                .findFirst();
        return accountFrmStore.orElse(null);

    }
}

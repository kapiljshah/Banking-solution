package com.bank.account;

import java.math.BigDecimal;

public class TransactionDetails {

    public TransactionDetails(String accountId, String transactionDate, String operation, BigDecimal transactAmunt,
                              BigDecimal balance, String decription) {
        this.accountId = accountId;
        this.transactionDate = transactionDate;
        this.operation = operation;
        this.transactAmunt = transactAmunt;
        this.balance = balance;
        this.decription = decription;
    }

    private String accountId;

    private String transactionDate;

    private String operation;

    private BigDecimal transactAmunt;

    private BigDecimal balance;

    private String decription;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public BigDecimal getTransactAmunt() {
        return transactAmunt;
    }

    public void setTransactAmunt(BigDecimal transactAmunt) {
        this.transactAmunt = transactAmunt;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }


    @Override
    public String toString() {
        return
                "accountId='" + accountId + " | " +
                ", transactionDate='" + transactionDate + " | " +
                ", operation='" + operation + " | " +
                ", transactAmunt='" + transactAmunt + " | " +
                ", balance='" + balance + " | " +
                ", decription='" + decription;
    }
}

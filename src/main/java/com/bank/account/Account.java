package com.bank.account;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Account {

    private String accountId;

    private BigDecimal balance;

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public Account(String accountId, BigDecimal balance){
        this.accountId = accountId;
        this.balance = balance;
    }

    public BigDecimal withdraw(BigDecimal amount) throws Exception {
        lock.readLock().lock();
        try {
            if (balance.compareTo(amount) != -1) {
                lock.readLock().unlock();
                lock.writeLock().lock();
                try {
                    balance = balance.subtract(amount);
                    lock.readLock().lock();
                }finally {
                    lock.writeLock().unlock();
                }
                return balance;
            } else {
                throw new Exception(" Account " + accountId + " does not have sufficient money to transfer. ");
            }
        }finally {
            lock.readLock().unlock();
        }
    }

    public BigDecimal deposit(BigDecimal amount){
        try {
            lock.writeLock().lock();
            try {
                balance = balance.add(amount);
                lock.readLock().lock();
            } finally {
                lock.writeLock().unlock();
            }
            return balance;
        }finally {
            lock.readLock().unlock();
        }

    }

    public BigDecimal getBalance() {
        lock.readLock().lock();
        try {
            return balance;
        }finally {
            lock.readLock().unlock();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return accountId.equals(account.accountId);
    }

    @Override
    public int hashCode() {
        return accountId.hashCode();
    }

    public String toString(){
        return accountId;
    }
}

package com.bank.account;

import com.bank.dao.AccountManagerDaoImpl;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/*
 *  Class responsible to transfer the amount from one account to another account
 */
public class AccountServiceImpl implements AccountService{


    //private AccountManagerDao accountManagerDao = new A;

//    ExecutorService executorService = Executors.newFixedThreadPool(10);

    public String transfer(String fromAccId, String toAccId, BigDecimal amountToTransfer) throws Exception {
 //       Callable<String> call = () -> {
            try{
                if (amountToTransfer != null && amountToTransfer.compareTo(new BigDecimal(0)) == -1) {
                    throw new Exception("Please provide proper amount to transfer.");
                }

                Account fromTmp = new Account(fromAccId, null);
                Account from = AccountManagerDaoImpl.INSTANCE.getAccount(fromTmp);
                Account toTmp = new Account(toAccId, null);
                Account to = AccountManagerDaoImpl.INSTANCE.getAccount(toTmp);

                BigDecimal fromAccBal = from.withdraw(amountToTransfer);
                Account accToStore = new Account(fromAccId, fromAccBal);
                TransactionDetails transactionDetails = new TransactionDetails(fromAccId, new Date().toString(), WITHDRAW,
                        amountToTransfer, fromAccBal, "");
                AccountManagerDaoImpl.INSTANCE.storeDetails(accToStore, transactionDetails);
                System.out.println("Balance of Account " + from.toString() + " after withdrawal - " + fromAccBal);
                System.out.println(Thread.currentThread().getName() + " Account Details of account " + from.toString() + " Initial balance:- " + from.getBalance()
                        + " Amount to withdraw:- " + amountToTransfer + " Balance after withdraw:- " + from.getBalance());
                Thread.sleep(10000);

                BigDecimal toAccBal = to.deposit(amountToTransfer);
                Account accStore = new Account(toAccId, toAccBal);
                TransactionDetails transactionDetails1 = new TransactionDetails(toAccId, new Date().toString(), DEPOSIT,
                            amountToTransfer, toAccBal, "");
                AccountManagerDaoImpl.INSTANCE.storeDetails(accStore, transactionDetails1);
                System.out.println("Balance of Account " + to.toString() + " after deposit - " + toAccBal);
                System.out.println(Thread.currentThread().getName() + " Account Details of account " + to.toString() + " Initial balance:- " + to.getBalance()
                        + " Amount to deposit:- " + amountToTransfer + " Balance after deposit:- " + to.getBalance());
                return fromAccBal.toString();
            }catch (Exception e){
                e.printStackTrace();
                throw e;
                //return "Fail";
            }
//        };
//        Future<String> status = executorService.submit(call);
//        return status.get();
    }

    public String deposit(String toAccId, BigDecimal amountToDeposit) throws Exception {
  //      Callable<String> call = () -> {
            try{
                if(amountToDeposit != null && amountToDeposit.compareTo(new BigDecimal(0))  == -1 )    {
                    throw new Exception("Please provide proper amount to deposit.");
                }
                Account toTmp  = new Account(toAccId, null);
                Account to = AccountManagerDaoImpl.INSTANCE.getAccount(toTmp);
                if(to == null ) {
                    Account accStore = new Account(toAccId, amountToDeposit);
                    TransactionDetails transactionDetails1 = new TransactionDetails(toAccId, new Date().toString(), DEPOSIT,
                            amountToDeposit, amountToDeposit, "");
                    AccountManagerDaoImpl.INSTANCE.storeDetails(accStore, transactionDetails1);
                }else{
                    BigDecimal toAccBal  = to.deposit(amountToDeposit);
                    Account accStore = new Account(toAccId, toAccBal);
                    TransactionDetails transactionDetails1 = new TransactionDetails(toAccId, new Date().toString(), DEPOSIT,
                            amountToDeposit, toAccBal, "");
                    AccountManagerDaoImpl.INSTANCE.storeDetails(accStore, transactionDetails1);
                }
                BigDecimal balance = getBalance(toAccId);
                System.out.println("Balance of Account " + toAccId + " after deposit - " + balance);
                return balance.toString();
            }catch (Exception e){
                e.printStackTrace();
                throw e;
                //return "Fail";
            }
//        };
//        Future<String> status = executorService.submit(call);
//        return status.get();
    }

    public String getStatement(String accountId, String fromDate, String toDate)throws Exception{
        //Callable<String> call = () -> {
            try {
                List<String> statement = AccountManagerDaoImpl.INSTANCE.getStatement(accountId, fromDate, toDate);
                return statement
                        .stream()
                        .collect(Collectors.joining("\n"));
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
                //return "Fail";
            }
 //       };
//        Future<String> status = executorService.submit(call);
//        return status.get();
    }

    public String withdraw(String fromAccId, BigDecimal amountToWithdraw)throws Exception {
   //     Callable<String> call = () -> {
            try {
               if(amountToWithdraw != null && amountToWithdraw.compareTo(new BigDecimal(0))  == -1 )    {
                throw new Exception("Please provide proper amount to withdraw.");
                }
                Account fromTmp  = new Account(fromAccId, null);
                Account from = AccountManagerDaoImpl.INSTANCE.getAccount(fromTmp);
                BigDecimal fromAccBal  = from.withdraw(amountToWithdraw);
                Account accToStore = new Account(fromAccId, fromAccBal);
                TransactionDetails transactionDetails = new TransactionDetails(fromAccId, new Date().toString(), WITHDRAW,
                                amountToWithdraw, fromAccBal, "");
                AccountManagerDaoImpl.INSTANCE.storeDetails(accToStore, transactionDetails);
                System.out.println("Balance of Account " + from.toString() + " after withdrawal - " + fromAccBal);
                return "Success";
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }
//        };
//        Future<String> status = executorService.submit(call);
//        return status.get();
    }

    public BigDecimal getBalance(String accId)throws Exception {
        //Callable<String> call = () -> {
            try {
                Account fromTmp  = new Account(accId, null);
                Account from = AccountManagerDaoImpl.INSTANCE.getAccount(fromTmp);
                BigDecimal fromAccBal  = from.getBalance();
                System.out.println("Balance of Account " + from.toString() + " is - " + fromAccBal);
                return fromAccBal;
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }
//        };
//        Future<String> balance = executorService.submit(call);
//        return balance.get();
    }


}

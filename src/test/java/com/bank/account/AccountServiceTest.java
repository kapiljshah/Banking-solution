package com.bank.account;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class AccountServiceTest {

    final AccountService accService = new AccountServiceImpl();
    String account1 = "123";
    String account2 = "234";

    @Before
    public void setUp() throws Exception {
        accService.deposit(account1, new BigDecimal(100));
        accService.deposit(account2, new BigDecimal(20));
    }

    /*
     * Tests the amount transfer between two accounts at same time using two threads
     */
    @Test
    public void testAccountService() throws Exception {

        Runnable r1 = () -> {
            try {
                accService.transfer(account1, account2, new BigDecimal(20));
            } catch (Exception e) {
                System.out.println("Error while transferring the amount from account " + account1.toString() + " to account "
                        + account2.toString() + " " + e.getMessage());
            }
        };

        Runnable r2 = () -> {
            try {
                accService.transfer(account2, account1, new BigDecimal(10));
            } catch (Exception e) {
                System.out.println("Error while transferring the amount from account " + account2.toString() + " to account "
                        + account1.toString() + "........ " + e.getMessage());
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Balance of account1 " + accService.getBalance(account1));
        System.out.println("Balance of account2 " + accService.getBalance(account2));
        Assert.assertTrue("Balance of account1 " , accService.getBalance(account1).equals(new BigDecimal(90)));
        Assert.assertTrue("Balance of account2 " , accService.getBalance(account2).equals(new BigDecimal(30)));
    }

    /*
        Test will show error if negative amount is passesed for transfer.
     */
    @Test
    public void testAccountServiceWithNegativeAmt() throws Exception {
        Runnable r1 = () -> {
            try {
                accService.transfer(account1, account2, new BigDecimal(-20));
            } catch (Exception e) {
                System.out.println("Error while transferring the amount from account " + account1.toString() + " to account "
                        + account2.toString() + "........ " + e.getMessage());
            }
        };

        Thread t1 = new Thread(r1);
        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue("Balance of account1 " , accService.getBalance(account1).equals(new BigDecimal(100)));
    }

    /*
        Test will throw an exception if negative amount is entered for transfer.
     */
    @Test (expected = Exception.class)
    public void testAccountServiceWithNegativeAmtWithException() throws Exception {

        accService.transfer(account1, account2, new BigDecimal(-20));
    }

    /*
        Test will work if 0 amount is pass for transfer no amount will be transfered.
     */
    @Test
    public void testAccountServiceWithZeroAmt() throws Exception {

        Runnable r1 = () -> {
            try {
                accService.transfer(account1, account2, new BigDecimal(0));
            } catch (Exception e) {
                System.out.println("Error while transferring the amount from account " + account1.toString() + " to account "
                        + account2.toString() + "........ " + e.getMessage());
            }
        };

        Thread t1 = new Thread(r1);
        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue("Balance of account1 " , accService.getBalance(account1).equals(new BigDecimal(100)));
    }


    /*
        Test will show an error if from account do not have sufficient balance in the from account for transfer.
     */
    @Test
    public void testAccountServiceInsufficientBalance() throws Exception {

        Runnable r1 = () -> {
            try {
                accService.transfer(account1, account2, new BigDecimal(200));
            } catch (Exception e) {
                System.out.println("Error while transferring the amount from account " + account1.toString() + " to account "
                        + account2.toString() + "..... " + e.getMessage());
            }
        };

        Thread t1 = new Thread(r1);
        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            Assert.assertTrue("Balance of account1 ", accService.getBalance(account1).equals(new BigDecimal(100)));
            Assert.assertTrue("Balance of account2 ", accService.getBalance(account2).equals(new BigDecimal(20)));

    }

    @Test
    public void testData() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();
            int n = Integer.parseInt(line);
            int a[] = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int i =0;
            while(st.hasMoreTokens()){
                a[i++] = Integer.parseInt(st.nextToken());
            }

            for(int j = 0; j < n; j++ ){
                int num = a[j];
                for(int k = 1; k <= num; ++k){
                    if(k % 15 == 0){
                        System.out.println("FizzBuzz");
                    }else if (k % 3 == 0){
                        System.out.println("Fizz");
                    }else if(k % 5 == 0){
                        System.out.println("Buzz");
                    }
                }
            }

        }
    }


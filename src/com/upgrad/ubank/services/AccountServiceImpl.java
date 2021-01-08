package com.upgrad.ubank.services;

import com.upgrad.ubank.dtos.Account;
import com.upgrad.ubank.dtos.Transaction;

public class AccountServiceImpl implements AccountService {
    //Account array to store account objects for the application, later in the course
    //this array will be replaced with database
    private TransactionService transactionService;      //10
    private Account[] accounts;

    //counter is used to track how many accounts are present in the account array


    private int counter;

    public AccountServiceImpl(TransactionService transactionService) {

        this.transactionService=transactionService;
        accounts = new Account[100];
        counter = 0;
    }

    public boolean login (Account account) {
        for (int i = 0; i < counter; i++) {
            if (account.getAccountNo() == accounts[i].getAccountNo() && account.getPassword().equals(accounts[i].getPassword())) {
                return true;
            }
        }
        return false;
    }

    public boolean register (Account account) {
        for (int i = 0; i < counter; i++) {
            if (account.getAccountNo() == accounts[i].getAccountNo()) {
                return false;
            }
        }

        account.setBalance(0);
        accounts[counter++] = account;
        return true;
    }

    @Override
    public Account getAccount(int accountNo) {
        for (int i=0; i<counter; i++) {
            if (accounts[i].getAccountNo() == accountNo) {
                return accounts[i];
            }
        }
        return null;
    }

    @Override
    public Account deposit(int accountNo, int amount) {

        Account account = getAccount(accountNo);    // got the acc obj
        if(account==null)
        {
            return null;
        }
        account.setBalance(account.getBalance()+amount);    // set=current+prev
        // create a TRansaction Object,...set the attribute of the transaction obj....call the create transaction method..
        //print transaction // return the account obj

        Transaction transaction=new Transaction(accountNo,"07-01-2021","deposite",amount);
        transactionService.createTransaction(transaction);
        System.out.println(transaction);
                                                //11

        return account;
    }

    /*
     * A account holder can overdraw up to 1000 rs. This decision was made on
     * 13 July 2020. Please refer the business documents for more information.
     */
    @Override
    public Account withdraw(int accountNo, int amount) {



        Account account = getAccount(accountNo);
        if (account == null) {
            return null;
        }
        if ((account.getBalance() + 1000) < amount) {
            return null;
        }
        account.setBalance(account.getBalance() - amount);

        Transaction transaction = new Transaction();
        transaction.setAccountNo(accountNo);
        transaction.setDate("DD/MM/YYYY");
        transaction.setAction("Withdraw");
        transaction.setAmount(amount);
        System.out.println(transactionService.createTransaction(transaction));//12

        return account;
    }
}

package com.upgrad.ubank.services;

public class ServiceFactory {
    public TransactionService getTransactionService () {
        return TransactionServiceImpl.getInstance();
    }

    public AccountService getAccountService () {
        return AccountServiceImpl.getInstance();
    }
}

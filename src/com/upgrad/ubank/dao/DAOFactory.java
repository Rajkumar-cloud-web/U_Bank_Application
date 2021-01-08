package com.upgrad.ubank.dao;

public class DAOFactory {
    public AccountDAO getAccountDAO () {
        return AccountDAOImpl.getInstance();
    }

    public TransactionDAO getTransactionDAO () {
        return TransactionDAOImpl.getInstance();
    }
}

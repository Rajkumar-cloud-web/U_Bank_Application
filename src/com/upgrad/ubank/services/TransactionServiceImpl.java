package com.upgrad.ubank.services;

import com.upgrad.ubank.dao.DAOFactory;
import com.upgrad.ubank.dao.TransactionDAO;

import com.upgrad.ubank.dtos.Transaction;
import com.upgrad.ubank.interfaces.Observer;
import com.upgrad.ubank.interfaces.Subject;

import java.sql.SQLException;
import java.util.List;

public class TransactionServiceImpl implements TransactionService, Observer {

    private static TransactionServiceImpl instance = new TransactionServiceImpl();

    private Subject accountServiceSubject;
    private ServiceFactory serviceFactory;

    private DAOFactory daoFactory;
    private TransactionDAO transactionDAO;

    private TransactionServiceImpl () {
        serviceFactory = new ServiceFactory();
        accountServiceSubject = (Subject) serviceFactory.getAccountService();
        accountServiceSubject.registerObserver(this);

        daoFactory = new DAOFactory();
        transactionDAO = daoFactory.getTransactionDAO();
    }

    public static TransactionServiceImpl getInstance() {
        if (instance == null) {
            instance = new TransactionServiceImpl();
        }
        return instance;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) throws Exception {
        Transaction temp = null;
        try {
            temp = transactionDAO.create(transaction);
        } catch (SQLException e) {
            throw new Exception("Some unexpected exception occurred");
        }
        return temp;
    }

    @Override
    public List<Transaction> getTransactions(int accountNo) throws Exception {
        List<Transaction> temp;
        try {
            temp = transactionDAO.findByAccountNo(accountNo);
        } catch (SQLException e) {
            throw new Exception("Some unexpected exception occurred.");
        }
        return temp;
    }

    @Override
    public void update(Object data) {
        if (data instanceof Transaction) {
            Transaction temp = (Transaction) data;
            try {
                createTransaction(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package com.upgrad.ubank.services;

import com.upgrad.ubank.dtos.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction (Transaction transaction) throws Exception;
    List<Transaction> getTransactions (int accountNo) throws Exception;
}

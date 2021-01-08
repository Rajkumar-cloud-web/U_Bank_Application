package com.upgrad.ubank.dao;

import com.upgrad.ubank.dtos.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface TransactionDAO {
    public Transaction create (Transaction transaction) throws SQLException;
    public List<Transaction> findByAccountNo (int accountNo) throws SQLException;
}

package com.upgrad.ubank.dao;

import com.upgrad.ubank.dtos.Account;

import java.sql.SQLException;

public interface AccountDAO {
    public Account findByAccountNo (int accountNo) throws SQLException;
    public Account create (Account account) throws SQLException;
    public Account updateBalance (Account account) throws SQLException;
}

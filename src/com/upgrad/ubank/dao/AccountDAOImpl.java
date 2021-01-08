package com.upgrad.ubank.dao;

import com.upgrad.ubank.db.Database;
import com.upgrad.ubank.dtos.Account;
import com.upgrad.ubank.utils.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAOImpl implements AccountDAO {

    private static AccountDAOImpl instance;

    private AccountDAOImpl () {}

    public static AccountDAOImpl getInstance() {
        if (instance == null) {
            instance = new AccountDAOImpl();
        }
        return instance;
    }

    @Override
    public Account findByAccountNo(int accountNo) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        Logger.log("Executing AccountDAOImpl.findByAccountNo() for " + accountNo);
        String sql = "SELECT * FROM account WHERE accountNo = " + accountNo;
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            Account account = new Account();
            account.setAccountNo(resultSet.getInt("accountNo"));
            account.setPassword(resultSet.getString("password"));
            account.setBalance(resultSet.getInt("balance"));
            return account;
        } else {
            return null;
        }
    }

    @Override
    public Account create(Account account) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        Logger.log("Executing AccountDAOImpl.create() for " + account.getAccountNo());
        String sql = "INSERT INTO account (accountNo, password, balance) VALUES (" +
                account.getAccountNo() + ", '" +
                account.getPassword() + "', " +
                account.getBalance()+
                ")";
        statement.executeUpdate(sql);
        return account;
    }

    //

    @Override
    public Account updateBalance(Account account) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        Logger.log("Executing AccountDAOImpl.updateBalance() for " + account.getAccountNo());
        String sql = "UPDATE account SET balance = '" +
                account.getBalance()+ "' WHERE accountNo = " +
                account.getAccountNo();
        statement.executeUpdate(sql);
        return account;
    }

    public static void main(String[] args) throws SQLException {
        AccountDAOImpl accountDAO = AccountDAOImpl.getInstance();

        System.out.println(accountDAO.findByAccountNo(400001));

        Account account = new Account(400003, "ashita123", 0);
        accountDAO.create(account);
        System.out.println(accountDAO.findByAccountNo(400003));

        Account updateAccount = new Account(400003, "ashita123", 1000);
        accountDAO.updateBalance(updateAccount);
        System.out.println(accountDAO.findByAccountNo(400003));
    }
}

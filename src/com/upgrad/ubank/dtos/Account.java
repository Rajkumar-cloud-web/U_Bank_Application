package com.upgrad.ubank.dtos;

public class Account {
    private int accountNo;
    private String password;
    private int balance;

    public Account() {
    }

    public Account(int accountNo, String password, int balance) {
        this.accountNo = accountNo;
        this.password = password;
        this.balance = balance;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return  "AccountNo: " + accountNo + "\n" +
                "Balance: " + balance;
    }
}

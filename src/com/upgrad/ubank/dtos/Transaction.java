package com.upgrad.ubank.dtos;

import java.io.Serializable;

public class Transaction implements Serializable {
    private int accountNo;
    private String date;
    private String action;
    private int amount;

    public Transaction() {
    }

    public Transaction(int accountNo, String date, String action, int amount) {
        this.accountNo = accountNo;
        this.date = date;
        this.action = action;
        this.amount = amount;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return date + '\t' + action + '\t' + amount;
    }
}

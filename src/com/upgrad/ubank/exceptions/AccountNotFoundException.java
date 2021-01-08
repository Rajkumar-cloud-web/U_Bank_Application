package com.upgrad.ubank.exceptions;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException (String message) {
        super(message);
    }
}

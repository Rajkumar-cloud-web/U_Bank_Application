package com.upgrad.ubank.exceptions;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException (String message) {
        super(message);
    }
}

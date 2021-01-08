package com.upgrad.ubank.exceptions;

public class AccountAlreadyRegisteredException extends Exception {
    public AccountAlreadyRegisteredException (String message) {
        super(message);
    }
}

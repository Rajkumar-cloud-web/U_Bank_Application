package com.upgrad.ubank.exceptions;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException (String message) {
        super(message);
    }
}

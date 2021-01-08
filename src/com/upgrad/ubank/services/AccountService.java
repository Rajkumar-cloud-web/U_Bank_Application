package com.upgrad.ubank.services;

import com.upgrad.ubank.dtos.Account;

public interface AccountService {
    boolean login (Account account);
    boolean register (Account account);
    Account getAccount (int accountNo);
    Account deposit (int accountNo, int amount);
    Account withdraw (int accountNo, int amount);
}

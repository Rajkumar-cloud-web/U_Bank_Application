package com.upgrad.ubank.services;

import com.upgrad.ubank.dtos.Account;

public interface AccountService {
    boolean login (Account account) throws Exception;
    boolean register (Account account) throws Exception;
    Account getAccount (int accountNo) throws Exception;
    Account deposit (int accountNo, int amount) throws Exception;
    Account withdraw (int accountNo, int amount) throws Exception;
}

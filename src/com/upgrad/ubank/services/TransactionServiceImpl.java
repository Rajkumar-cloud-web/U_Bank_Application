package com.upgrad.ubank.services;

import com.upgrad.ubank.dtos.Transaction;

public class TransactionServiceImpl implements TransactionService{

    private Transaction[] transactions;

    private int count;

    public TransactionServiceImpl()
    {
        transactions=new Transaction[100];
        count=0;
    }


    @Override
    public Transaction createTransaction(Transaction transaction) {

        transactions[count]=transaction;
        count=count+1;

        return transaction;
    }

    @Override
    public Transaction[] getTransactions(int accountNo) {

        Transaction[] tempTransactions=new Transaction[100];
        int tempCount=0;

        for(int i=0; i<transactions.length; i++)
        {
            if(transactions[i].getAccountNo()==accountNo)
            {
                tempTransactions[tempCount]=transactions[i];
                tempCount=tempCount+1;
            }

        }

        return tempTransactions;
        //return new Transaction[0];
    }
}

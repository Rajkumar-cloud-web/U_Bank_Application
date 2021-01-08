package com.upgrad.ubank.utils;

import com.upgrad.ubank.dtos.Transaction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionReadAndWrite {
    public static void writeTransactions (List<Transaction> transactions) {
        String filePath = "C:\\Users\\ishwar.soni\\Downloads\\objFile.txt";
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        ) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Transaction> readTransactions () {
        String filePath = "C:\\Users\\ishwar.soni\\Downloads\\objFile.txt";
        List<Transaction> transactions = null;
        try (
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        ) {
            transactions = (List<Transaction>)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1234, "29/07/2020", "DEPOSIT", 10000));
        transactions.add(new Transaction(1234, "29/07/2020", "WITHDRAW", 5000));
        transactions.add(new Transaction(1234, "29/07/2020", "DEPOSIT", 1000));
        transactions.add(new Transaction(1234, "29/07/2020", "WITHDRAW", 2000));
        writeTransactions(transactions);
        System.out.println(readTransactions());
    }
}

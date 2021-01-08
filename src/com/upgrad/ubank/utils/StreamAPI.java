package com.upgrad.ubank.utils;

import com.upgrad.ubank.dtos.Transaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPI {
    public static List<Transaction> transactions = new ArrayList<>();

    public static void printTransactions () {
        transactions.forEach(System.out::println);
    }

    public static void filterByAction () {
        transactions.stream()
                .filter(t -> t.getAction().equals("DEPOSIT"))
                .forEach(System.out::println);
    }

    public static void printAmounts () {
        transactions.stream()
                .map(Transaction::getAmount)
                .forEach(System.out::println);
    }

    public static void printTransactionByIncreasingAmount () {
        transactions.stream()
                .sorted(Comparator.comparing(Transaction::getAmount))
                .forEach(System.out::println);
    }

    public static void printAmountSum () {
        int sum = transactions.stream().map(Transaction::getAmount)
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }

    public static List<Integer> getSortedDepositAmountByAccountNo (int accountNo) {
        return transactions.stream()
                .filter(t -> t.getAccountNo() == accountNo)
                .filter(t -> t.getAction().equals("DEPOSIT"))
                .map(Transaction::getAmount)
                .sorted()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        transactions.add(new Transaction(1234, "29/07/2020", "DEPOSIT", 10000));
        transactions.add(new Transaction(1234, "29/07/2020", "WITHDRAW", 5000));
        transactions.add(new Transaction(1234, "30/07/2020", "WITHDRAW", 4000));
        transactions.add(new Transaction(1234, "30/07/2020", "DEPOSIT", 6000));
        transactions.add(new Transaction(5678, "30/07/2020", "DEPOSIT", 10000));
        transactions.add(new Transaction(5678, "31/07/2020", "WITHDRAW", 5000));
        transactions.add(new Transaction(5678, "31/07/2020", "DEPOSIT", 12000));
        transactions.add(new Transaction(5678, "01/08/2020", "DEPOSIT", 1000));

        //printTransactions();
        //filterByAction();
        //printAmounts();
        //printTransactionByIncreasingAmount();
        //printAmountSum();
        System.out.println(getSortedDepositAmountByAccountNo(1234));
    }
}

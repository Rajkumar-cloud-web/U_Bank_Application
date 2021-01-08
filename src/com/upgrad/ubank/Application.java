package com.upgrad.ubank;

import com.upgrad.ubank.dtos.Account;
import com.upgrad.ubank.dtos.Transaction;
import com.upgrad.ubank.services.*;

import java.util.Scanner;

public class Application {
    private Scanner scan;

    private AccountService accountService;
    private TransactionService transactionService;

    //a flag used to check whether a user is logged in or not
    private boolean isLoggedIn;

    //an attribute to store account no of the logged in user
    private int loggedInAccountNo;

    public Application (AccountService accountService, TransactionService transactionService) {
        scan = new Scanner(System.in);
        this.accountService = accountService;
        this.transactionService = transactionService;
        isLoggedIn = false;
        loggedInAccountNo = 0;
    }

    private void start () {
        boolean flag = true;

        System.out.println("*********************");
        System.out.println("********U-Bank*******");
        System.out.println("*********************");

        do {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Account Statement");
            System.out.println("7. Logout");
            System.out.println("8. Exit");

            System.out.print("\nPlease select an option: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1": login(); break;
                case "2": register(); break;
                case "3": getAccount(); break;
                case "4": deposit(); break;
                case "5": withdraw(); break;
                case "6": getAccountStatement(); break;
                case "7": logout(); break;
                case "8": flag=false; break;
                default:  System.out.println("Error"); break;
            }
        } while (flag);
    }

    //This method is used to perform login function for the user.
    //If the user is already logged in, then he won't be able to login again.
    //Also a user can only login, if the account no and password provided by
    //the user are present in the accounts array.
    private void login () {
        if (isLoggedIn) {
            System.out.println("You are already logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("********Login********");
        System.out.println("*********************");

        Account account = getAccountFromUser();

        if (accountService.login(account)) {
            System.out.println("You are logged in.");
            isLoggedIn = true;
            loggedInAccountNo = account.getAccountNo();
        } else {
            System.out.println("Incorrect Username / Password");
        }
    }

    //This method is used to perform register function for the user.
    //If the user is already logged in, then he won't be able to register.
    //Also a user can only register, if the account no and password provided by
    //the user are not present in the accounts array.
    private void register () {
        if (isLoggedIn) {
            System.out.println("You are already logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("******Register*******");
        System.out.println("*********************");

        Account account = getAccountFromUser();

        if (accountService.register(account)) {
            System.out.println("You are logged in.");
            isLoggedIn = true;
            loggedInAccountNo = account.getAccountNo();
        } else {
            System.out.println("User already exists.");
        }
    }

    private Account getAccountFromUser() {
        System.out.print("Account No.:");
        int accountNo = Integer.parseInt(scan.nextLine());

        System.out.print("Password:");
        String password = scan.nextLine();

        Account account = new Account();
        account.setAccountNo(accountNo);
        account.setPassword(password);
        return account;
    }

    private void getAccount () {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("*******Account*******");
        System.out.println("*********************");

        //System.out.println("Get the account corresponding to Account No: " + loggedInAccountNo);
        System.out.println(accountService.getAccount(loggedInAccountNo));       //13
    }

    private void deposit () {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("*******Deposit*******");
        System.out.println("*********************");

        System.out.print("Amount: ");
        int amount = Integer.parseInt(scan.nextLine());

        Account account = accountService.deposit(loggedInAccountNo,amount);
        if(account==null)
        {
            System.out.println("Could not deposit into account");
        }else
            {
                System.out.println("Money successfully deposited into account");
            }
                                                                                                                    //System.out.println("Deposit " + amount + " rs to account " + loggedInAccountNo);
    }

    private void withdraw () {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("******Withdraw*******");
        System.out.println("*********************");

        System.out.print("Amount: ");
        int amount = Integer.parseInt(scan.nextLine());

        Account account = accountService.withdraw(loggedInAccountNo, amount);
        if (account == null) {
            System.out.println("Could not withdraw from account.");
        } else {
            System.out.println("Money successfully withdrawn from account.");
        }
    }

    private void getAccountStatement() {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("**Account Statement**");
        System.out.println("*********************");

        Transaction[] transactions = transactionService.getTransactions(loggedInAccountNo);
        if (transactions == null) {
            System.out.println("This feature is not available for mobile");
            return;
        } else if(transactions[0]==null){

            System.out.println("No transaction exists for you");
            return;
            //System.out.println("Print account statement for account " + loggedInAccountNo);
        }
        for(Transaction transaction: transactions)
        {
            if(transaction==null)
            {
                break;
            }
            System.out.println(transaction);
        }
    }

    private void logout () {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }
        System.out.println("Logged out successfully");
        isLoggedIn = false;
        loggedInAccountNo = 0;
    }

    public static void main(String[] args) {

        TransactionService transactionService = new TransactionServiceImpl();   //10
        AccountService accountService = new AccountServiceImpl(transactionService);
        //TransactionService transactionService = new TransactionServiceImplMobile();

        Application application = new Application(accountService, transactionService);
        application.start();
    }
}

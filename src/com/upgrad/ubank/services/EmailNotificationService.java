package com.upgrad.ubank.services;

import com.upgrad.ubank.dtos.Transaction;
import com.upgrad.ubank.interfaces.Observer;
import com.upgrad.ubank.interfaces.Subject;
import com.upgrad.ubank.utils.Logger;

public class EmailNotificationService implements NotificationService, Observer {

    private static EmailNotificationService instance = new EmailNotificationService();
    private ServiceFactory serviceFactory;
    private Subject accountServiceSubject;

    private EmailNotificationService () {
        serviceFactory = new ServiceFactory();
        accountServiceSubject = (Subject) serviceFactory.getAccountService();
        accountServiceSubject.registerObserver(this);
    }

    public static EmailNotificationService getInstance() {
        if (instance == null) {
            instance = new EmailNotificationService();
        }
        return instance;
    }

    @Override
    public void sendNotification(String message) {
        Logger.log(message);
    }

    @Override
    public void update(Object data) {
        if (data instanceof Transaction) {
            Transaction transaction = (Transaction) data;
            String message = "Email: " + transaction.toString();
            sendNotification(message);
        }
    }
}

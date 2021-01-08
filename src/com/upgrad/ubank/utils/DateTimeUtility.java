package com.upgrad.ubank.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtility {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println(localDate.format(DateTimeFormatter.ofPattern("dd MM yyyy")));
        System.out.println("----------");

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        System.out.println(localTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println(localTime.format(DateTimeFormatter.ofPattern("hh:mm a")));
    }
}

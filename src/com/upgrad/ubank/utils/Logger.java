package com.upgrad.ubank.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public static void log (String message) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String timeStamp = localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        String path = "./src/com/upgrad/ubank/logs/";
        String fileName = "logs " + date + ".log";
        String filePath = path + fileName;

        try {
            if (!Files.exists(Paths.get(path))) {
                Files.createDirectory(Paths.get(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(timeStamp + "\t" + message);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

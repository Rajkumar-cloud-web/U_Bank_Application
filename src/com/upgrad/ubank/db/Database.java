package com.upgrad.ubank.db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Connection connection = null;

    public static Connection getConnection () {
        if (connection == null) {
            Map<String, String> credentials = null;
            try (BufferedReader br = new BufferedReader(new FileReader("./src/com/upgrad/ubank/db/database.config"))) {
                credentials = new HashMap<>();
                String line;
                while ((line=br.readLine()) != null) {
                    String[] tokens = line.split("=");
                    credentials.put(tokens[0], tokens[1]);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Config file not found.");
            } catch (IOException e) {
                System.out.println("Error while reading config file.");
            }
            String url = credentials.get("url");
            String username = credentials.get("username");
            String password = credentials.get("password");

            try {
                Class.forName(credentials.get("driverName"));
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connected");
            } catch (ClassNotFoundException e) {
                System.out.println(credentials.get("errorMessage"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private Database() {}

    public static void main(String[] args) {
        Database.getConnection();
    }
}

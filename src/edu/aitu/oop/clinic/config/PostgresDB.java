package edu.aitu.oop.clinic.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresDB {
    private static PostgresDB instance;

    private Connection connection;

    private static final String URL = "jdbc:postgresql://aws-1-us-west-1.pooler.supabase.com:5432/postgres?sslmode=require";
    private static final String USER = "postgres.yerqnxxqvwuwjrpsghar";

    private PostgresDB() {
        try {
            String password = loadPassword();
            this.connection = DriverManager.getConnection(URL, USER, password);
            System.out.println("Singleton: Connection to DB established.");
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }


    public static PostgresDB getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new PostgresDB();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private String loadPassword() {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream("config.properties")) {
            props.load(input);
            String password = props.getProperty("DB_PASSWORD");
            if (password == null || password.isBlank()) {
                throw new RuntimeException("DB_PASSWORD is not set");
            }
            return password;
        } catch (IOException e) {
            throw new RuntimeException("Cannot load config.properties", e);
        }
    }
}

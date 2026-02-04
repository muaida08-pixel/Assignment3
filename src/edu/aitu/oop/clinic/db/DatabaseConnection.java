package edu.aitu.oop.clinic.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:postgresql://aws-1-ap-south-1.pooler.supabase.com:5432/postgres";

    private static final String USER = "postgres.glayopnemfjbdpqexrhh";

    private static final String PASSWORD = "mohammad2005forogh";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("❌ Error connecting to database: " + e.getMessage());
            throw e;
        }
    }
}

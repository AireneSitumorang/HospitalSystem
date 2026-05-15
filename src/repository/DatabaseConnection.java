package repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:hospital.db";

    public static Connection getConnection() {
        try {
            // 🔥 PAKSA LOAD DRIVER
            Class.forName("org.sqlite.JDBC");

            return DriverManager.getConnection(URL);

        } catch (Exception e) {
            System.out.println("❌ Gagal koneksi ke database: " + e.getMessage());
            return null;
        }
    }
}
package HotelBooking;

import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:hotel.db";
    private Connection conn;

    public DatabaseManager() {
        connect();
        createTables();
    }

    private void connect() {
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connected to SQLite.");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Failed to close: " + e.getMessage());
        }
    }

    private void createTables() {
        String rooms = """
            CREATE TABLE IF NOT EXISTS rooms (
                room_number TEXT PRIMARY KEY,
                type TEXT NOT NULL,
                status TEXT NOT NULL,
                price REAL NOT NULL
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(rooms);
        } catch (SQLException e) {
            System.out.println("Table creation failed: " + e.getMessage());
        }
    }
}
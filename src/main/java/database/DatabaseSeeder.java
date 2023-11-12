package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSeeder {

    public static void main(String[] args) {

        String url = System.getenv("DB_URL");
        String username = "root";
        String password = System.getenv("DB_ROOT_PASS");;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Create tables
            createSubscriptionTable(connection);
            createLogTable(connection);
            createApikeyTable(connection);

            // Insert sample data
            insertSampleData(connection);

            System.out.println("Tables created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createSubscriptionTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE subscription (" +
                    "podcaster_username VARCHAR(255) PRIMARY KEY," +
                    "subscriber_username VARCHAR(255) PRIMARY KEY," +
                    "status VARCHAR(50) NOT NULL," +
                    "CHECK (status IN ('pending', 'accepted', 'rejected'))" +
                    ");");
        }
    }

    private static void createLogTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE log (" +
                    "log_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "log_endpoint_accessed VARCHAR(255)," +
                    "log_ip VARCHAR(255)," +
                    "log_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ");");
        }
    }

    private static void createApikeyTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE apikey (" +
                    "apikey_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "apikey_value VARCHAR(255) NOT NULL," +
                    "apikey_owner VARCHAR(255) NOT NULL," +
                    ");");
        }
    }

    private static void insertSampleData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // Insert sample data into the 'subscription' table
            statement.executeUpdate("INSERT INTO subscription (podcaster_username, subscriber_username, status ) VALUES " +
                    "('asep', 'dinda', 'pending')," +
                    "('aleks', 'mahfud', 'pending')," +
                    "('jokowi', 'bang prabs', 'pending')," +
                    "('jali', 'dinda', 'accepted')," +
                    "('ganjar', 'dinda', 'rejected')" +
                    ";");

            // Insert sample data into the 'log' table
            statement.executeUpdate("INSERT INTO log (log_endpoint_accessed, log_ip) VALUES " +
                    "('getSubs', '192.168.1.11')," +
                    "('createSubs', '192.168.1.13')," +
                    "('unknown', '192.168.10.128')" +
                    "");

            // Insert sample data into the 'apikey' table
            statement.executeUpdate("INSERT INTO apikey (apikey_value, apikey_owner) VALUES " +
                    "('xuyewPk6NDlBewftLtHJVf=PAb3', 'Milestone 2 REST Service)," +
                    "('gB9ACm8r5RZOBiN5ske9cBVjlVf', 'PHP Base App)," +
                    "");
        }
    }
}


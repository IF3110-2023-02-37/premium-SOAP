package database;

import java.sql.*;

public class DatabaseConnect {
    private static Connection connection = null;

    public DatabaseConnect (){
        String url = System.getenv("DB_URL");
        String username = "root";
        String password = System.getenv("DB_ROOT_PASS");

        try {
            this.connection = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet execute(String query) throws SQLException{

        Statement statement = connection.createStatement();
        return statement.executeQuery(query);

    }

    public int update(String query) throws SQLException{

        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);

    }

}

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

    public ResultSet execute(String query, Object... parameters) throws SQLException{

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        setParameter(preparedStatement, parameters);
        return preparedStatement.executeQuery();

    }

    public int update(String query, Object... parameters) throws SQLException{

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        setParameter(preparedStatement, parameters);
        return preparedStatement.executeUpdate();

    }

    private void setParameter(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 1; i < parameters.length+1; i++) {
            Object parameter = parameters[i-1];

            if (parameter instanceof String) {
                statement.setString(i, (String) parameter);
            } else if (parameter instanceof Integer) {
                statement.setInt(i, (Integer) parameter);
            }
        }
    }

}

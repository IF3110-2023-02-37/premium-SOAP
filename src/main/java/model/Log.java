package model;

import database.DatabaseConnect;

import java.sql.SQLException;

public class Log {
    public int log_id;
    public String log_endpoint_accessed;
    public String log_ip;
    public String log_source;
    public String authorization;
    public String log_timestamp;

    public Log (String log_endpoint_accessed, String log_ip, String log_source, String authorization){
        this.log_endpoint_accessed = log_endpoint_accessed;
        this.log_ip = log_ip;
        this.log_source = log_source;
        this.authorization = authorization;
    }

    public int saveToDatabase(DatabaseConnect connect) throws SQLException {
        int rowAffected = 0;
        String query = "INSERT INTO log (log_endpoint_accessed,log_ip,log_source,authorization) " +
                "VALUES (?,?,?,?);";

        rowAffected = connect.update(query,log_endpoint_accessed,log_ip,log_source,authorization);
        System.out.println(rowAffected);
        return rowAffected;
    }


}

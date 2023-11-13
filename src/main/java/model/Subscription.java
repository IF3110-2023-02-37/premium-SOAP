package model;

import database.DatabaseConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Subscription {
    public String podcaster_username;
    public String subscriber_username;
    public String status;

    public Subscription (String podcaster_username, String subscriber_username, String status){
        this.podcaster_username = podcaster_username;
        this.subscriber_username = subscriber_username;
        this.status = status;
    }

    public static List<Subscription> setToList(ResultSet setFromDatabase) throws SQLException {
        List<Subscription> subscriptionList = new ArrayList<>();

        while (setFromDatabase.next()) {
            String podcaster_username = setFromDatabase.getString("podcaster_username");
            String subscriber_username = setFromDatabase.getString("subscriber_username");
            String status = setFromDatabase.getString("status");

            Subscription subscription = new Subscription(podcaster_username, subscriber_username, status);
            subscriptionList.add(subscription);
        }
        return subscriptionList;
    }

    public int saveToDatabase(DatabaseConnect connect) throws SQLException{
        int rowAffected = 0;
        String query = "INSERT INTO subscription (podcaster_username,subscriber_username,status) " +
                "VALUES (?,?,?);";

        rowAffected = connect.update(query,podcaster_username,subscriber_username,status);
        System.out.println(rowAffected);
        return rowAffected;
    }

}

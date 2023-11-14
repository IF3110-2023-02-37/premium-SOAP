package model;

import database.DatabaseConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Key {
    public int apikey_id;
    public String apikey_value;
    public String apikey_owner;

    public Key (int id, String value, String owner){
        this.apikey_id = id;
        this.apikey_value = value;
        this.apikey_owner = owner;
    }

    public static List<Key> listOfKey() throws SQLException{
        List<Key> keyList = new ArrayList<>();
        DatabaseConnect connect = new DatabaseConnect();
        String query = "SELECT * FROM apikey;";

        ResultSet keySet = connect.execute(query);
        while (keySet.next()) {
            int apikey_id = keySet.getInt("apikey_id");
            String apikey_value = keySet.getString("apikey_value");
            String apikey_owner = keySet.getString("apikey_owner");

            Key key = new Key(apikey_id, apikey_value, apikey_owner);
            keyList.add(key);
        }

        return keyList;
    }
}

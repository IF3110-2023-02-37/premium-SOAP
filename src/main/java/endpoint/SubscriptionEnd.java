package endpoint;

import database.DatabaseConnect;
import model.Subscription;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebService
public class SubscriptionEnd {
    @WebMethod
    public List<Subscription> getSubs(@WebParam(name = "podcaster") String podcaster ) {

        DatabaseConnect connection = new DatabaseConnect();
        List<Subscription> subs = new ArrayList<>();
        String query = "SELECT * FROM subscription WHERE podcaster_username = '" + podcaster + "';";

        try{
            ResultSet subsFromDatabase = connection.execute(query);
            subs = Subscription.setToList(subsFromDatabase);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subs;
    }

    public List<Subscription> getPendingSubs(@WebParam(name = "podcaster") String podcaster ) {

        DatabaseConnect connection = new DatabaseConnect();
        List<Subscription> subs = new ArrayList<>();
        String query = "SELECT * FROM subscription WHERE "+
                "status = 'pending' AND podcaster_username = '" + podcaster + "';";

        try{
            ResultSet subsFromDatabase = connection.execute(query);
            subs = Subscription.setToList(subsFromDatabase);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subs;
    }

    @WebMethod
    public List<Subscription> getAllSubs() {
        DatabaseConnect connection = new DatabaseConnect();
        List<Subscription> subs = new ArrayList<>();
        String query = "SELECT * FROM subscription;";

        try{
            ResultSet subsFromDatabase = connection.execute(query);
            subs = Subscription.setToList(subsFromDatabase);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subs;
    }

    public String acceptSubs(@WebParam(name = "podcaster") String podcaster,
                             @WebParam(name= "subscriber") String subscriber) {
        DatabaseConnect connection = new DatabaseConnect();
        String response = "failed";
        int rowAffected = 0;

        String query = "UPDATE subscription SET status = 'accepted' " +
                        "WHERE podcaster_username = '" + podcaster + "' AND " +
                        "subscriber_username = '"+ subscriber +"'; ";
        try {
            rowAffected = connection.update(query);
        } catch (Exception e){
            e.printStackTrace();
        }

        if(rowAffected > 0 ){
            response = "success";
        }
        return response;
    }

    public String rejectSubs(@WebParam(name = "podcaster") String podcaster,
                             @WebParam(name= "subscriber") String subscriber) {
        DatabaseConnect connection = new DatabaseConnect();
        String response = "failed";
        int rowAffected = 0;

        String query = "UPDATE subscription SET status = 'rejected' " +
                "WHERE podcaster_username = '" + podcaster + "' AND " +
                "subscriber_username = '"+ subscriber +"'; ";
        try {
            rowAffected = connection.update(query);
        } catch (Exception e){
            e.printStackTrace();
        }

        if(rowAffected > 0 ){
            response = "success";
        }
        return response;
    }

    @WebMethod
    public String createPendingSubs(@WebParam(name = "podcaster") String podcaster,
                                    @WebParam(name= "subscriber") String subscriber) {
        DatabaseConnect connection = new DatabaseConnect();
        Subscription newSubs = new Subscription(podcaster,subscriber,"pending");
        String response = "failed";
        int rowAffected = 0;
        try{
            rowAffected = newSubs.saveToDatabase(connection);
        } catch (Exception e){
            e.printStackTrace();
        }

        if(rowAffected > 0){
            response = "success";
        }

        return response;
    }
}

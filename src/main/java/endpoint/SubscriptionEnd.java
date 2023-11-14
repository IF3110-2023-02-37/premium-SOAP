package endpoint;

import database.DatabaseConnect;
import model.Subscription;

import javax.annotation.Resource;
import javax.jws.*;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebService
@HandlerChain(file = "handler-config.xml")
public class SubscriptionEnd {

    @WebMethod
    public List<Subscription> getSubs(@WebParam(name = "podcaster") String podcaster ) {

        DatabaseConnect connection = new DatabaseConnect();
        List<Subscription> subs = new ArrayList<>();
        String query = "SELECT * FROM subscription WHERE podcaster_username = ?;";

        try{
            ResultSet subsFromDatabase = connection.execute(query, podcaster);
            subs = Subscription.setToList(subsFromDatabase);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subs;
    }
    @WebMethod
    public List<Subscription> getPendingSubs(@WebParam(name = "podcaster") String podcaster ) {

        DatabaseConnect connection = new DatabaseConnect();
        List<Subscription> subs = new ArrayList<>();
        String query = "SELECT * FROM subscription WHERE "+
                "status = 'pending' AND podcaster_username = ?;";

        try{
            ResultSet subsFromDatabase = connection.execute(query,podcaster);
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

        //System.out.println(this.wsContext.getMessageContext().get("Source"));
        return subs;
    }
    @WebMethod
    public String acceptSubs(@WebParam(name = "podcaster") String podcaster,
                             @WebParam(name= "subscriber") String subscriber) {
        DatabaseConnect connection = new DatabaseConnect();
        String response = "failed";
        int rowAffected = 0;

        String query = "UPDATE subscription SET status = 'accepted' " +
                        "WHERE podcaster_username = ? AND " +
                        "subscriber_username = ?; ";
        try {
            rowAffected = connection.update(query,podcaster, subscriber);
        } catch (Exception e){
            e.printStackTrace();
        }

        if(rowAffected > 0 ){
            response = "success";
        }
        return response;
    }
    @WebMethod
    public String rejectSubs(@WebParam(name = "podcaster") String podcaster,
                             @WebParam(name= "subscriber") String subscriber) {
        DatabaseConnect connection = new DatabaseConnect();
        String response = "failed";
        int rowAffected = 0;

        String query = "UPDATE subscription SET status = 'rejected' " +
                "WHERE podcaster_username = ? AND " +
                "subscriber_username = ?; ";
        try {
            rowAffected = connection.update(query,podcaster,subscriber);
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

    // DELETE endpoint for cascading deletion
    @WebMethod
    public String deleteSubsPodcaster(@WebParam(name = "podcaster") String podcaster){

        String response = "failed";
        DatabaseConnect connection = new DatabaseConnect();
        String query = "DELETE FROM subscription WHERE podcaster_username = ?";
        int rowAffected = 0;

        try{
            rowAffected = connection.update(query, podcaster);
        } catch (Exception e){
            e.printStackTrace();
        }

        if(rowAffected > 0){
            response = "success";
        }

        return response;
    }

    @WebMethod
    public String deleteSubsSubscriber(@WebParam(name = "subscriber") String subscriber){

        String response = "failed";
        DatabaseConnect connection = new DatabaseConnect();
        String query = "DELETE FROM subscription WHERE subscriber_username = ?";
        int rowAffected = 0;

        try{
            rowAffected = connection.update(query, subscriber);
        } catch (Exception e){
            e.printStackTrace();
        }

        if(rowAffected > 0){
            response = "success";
        }

        return response;
    }
}

import database.DatabaseConnect;
import endpoint.SubscriptionEnd;

import javax.xml.ws.Endpoint;

public class ServerJWS {
    public static void main(String[] args) {

        try{
            String address="http://0.0.0.0:8091/subscription";
            DatabaseConnect db = new DatabaseConnect();
            Endpoint.publish(address, new SubscriptionEnd());
            System.out.println("Running on Address : " + address);
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}

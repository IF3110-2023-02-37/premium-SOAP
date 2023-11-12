import route.Routing;
import endpoint.Subscription;

import javax.xml.ws.Endpoint;

public class ServerJWS {
    public static void main(String[] args) {

        try{
            String address="http://localhost:8090/subscription";
            Endpoint.publish(address, new Subscription());
            System.out.println("Running on Address : " + address);
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}

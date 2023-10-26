import server.tes;

import javax.xml.ws.Endpoint;

public class ServerJWS {
    public static void main(String[] args) {

        try{
            String address="http://localhost:8080/";
            Endpoint.publish(address, new tes());
            System.out.println("Running on Address : " + address);
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}

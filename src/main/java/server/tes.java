package server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService
public class tes {
    @WebMethod
    public String hello( @WebParam String name){

        return "Halo bang" + name;
    }
}

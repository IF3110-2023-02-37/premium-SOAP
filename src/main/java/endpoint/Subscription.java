package endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class Subscription {
    @WebMethod
    public String getSubs( @WebParam(name = "podcaster") String podcaster ) {
        return podcaster;
    }

    @WebMethod
    public String createSubs() {
        return "bang";
    }
}

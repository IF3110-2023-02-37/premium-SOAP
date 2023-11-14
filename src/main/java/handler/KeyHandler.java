package handler;
import model.Key;

import javax.mail.Message;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class KeyHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        boolean isResponse = (boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        String authorizationToken = "";
        try {
            // if the message is inbound (request)
            if (!isResponse) {
                SOAPHeader header = context.getMessage().getSOAPHeader();


                Iterator<SOAPHeaderElement> headerElements = header.getChildElements(new QName("http://schemas.xmlsoap.org/soap/envelope/", "Authorization", "soapenv"));
                if (headerElements.hasNext()) {
                    SOAPHeaderElement authHeader = headerElements.next();
                    authorizationToken = authHeader.getValue();
                    System.out.println("Authorization Token: " + authorizationToken);
                }

                // authorize
                Key requestKey = authorizationCheck(authorizationToken);
                //System.out.println(requestKey.apikey_owner);
                if (requestKey == null){
                    System.out.println("request key is null");
                    // Erase the message (request)
                    SOAPBody body = context.getMessage().getSOAPBody();
                    body.removeContents();

                    SOAPFault fault = body.addFault();
                    fault.setFaultCode("401");
                    fault.setFaultString("Unauthorized Access");
                    return false;
                } else {
                    context.put("Source", requestKey.apikey_owner);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }

    private Key authorizationCheck(String key) throws SQLException {

        List<Key> keyList = Key.listOfKey();

        for (Key element : keyList){
            if(element.apikey_value.equals(key)) {
                return element;
            }
        }
        return null;
    }

    private SOAPFault createSOAPFault(String faultCode, String faultString) throws SOAPException {
        SOAPFactory soapFactory = SOAPFactory.newInstance();
        SOAPFault soapFault = soapFactory.createFault();
        soapFault.setFaultCode(faultCode);
        soapFault.setFaultString(faultString);
        return soapFault;
    }
}


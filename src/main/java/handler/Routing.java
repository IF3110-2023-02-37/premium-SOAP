package handler;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;


@WebService
public class Routing implements SOAPHandler<SOAPMessageContext> {
    @Override
    public boolean handleMessage(SOAPMessageContext context){
        Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        // incoming request
        if(!outbound){
            try {
                SOAPMessage message = context.getMessage();
                SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
                SOAPHeader header = envelope.getHeader();

            } catch (Exception e) {
                e.printStackTrace();
            }
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

    @Override
    public Set<QName> getHeaders() {
        return null;
    }
}

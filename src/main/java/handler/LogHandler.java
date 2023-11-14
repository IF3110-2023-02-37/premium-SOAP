package handler;

import com.sun.net.httpserver.HttpExchange;
import database.DatabaseConnect;
import model.Log;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;

public class LogHandler implements SOAPHandler<SOAPMessageContext> {
    @Resource
    private WebServiceContext wsContext;
    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        boolean isResponse = (boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        String source;
        String authorization;
        try {
            if (isResponse) {
                source = "SOAP Service";
                authorization = "none";
            } else {
                source = (String) context.get("Source");
                authorization = (String) context.get("Authorization");
            }

            // get endpoint
            QName operation = (QName) context.get(MessageContext.WSDL_OPERATION);
            String endpoint = operation.getLocalPart();
            System.out.println("Endpoint: " + endpoint);

            // get ip
            MessageContext mc = context;
            HttpExchange httpExchange = (HttpExchange) mc.get("com.sun.xml.internal.ws.http.exchange");
            InetSocketAddress remoteAddress = (InetSocketAddress) httpExchange.getRemoteAddress();
            String ip = remoteAddress.getAddress().getHostAddress();
            System.out.println("Ip Address: " + ip);


            int rowAffected = logMessage(endpoint,ip,source,authorization);

            if(!(rowAffected > 0)) {
                SOAPFault fault = createSOAPFault("Logging Failed", "Log database is not changed");
                throw new SOAPFaultException(fault);
            }
        } catch (Exception e){
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

    public int logMessage(String endpoint, String ip, String source, String authorization) throws SQLException {
        int rowAffected = 0;
        DatabaseConnect connect = new DatabaseConnect();
        Log log = new Log(endpoint,ip,source,authorization);
        rowAffected = log.saveToDatabase(connect);
        return rowAffected;
    }

    private SOAPFault createSOAPFault(String faultCode, String faultString) throws SOAPException {
        SOAPFactory soapFactory = SOAPFactory.newInstance();
        SOAPFault soapFault = soapFactory.createFault();
        soapFault.setFaultCode(faultCode);
        soapFault.setFaultString(faultString);
        return soapFault;
    }

    private void logRequestHeaders(HttpServletRequest request) {
        if (request != null) {
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                System.out.println(headerName + ": " + headerValue);
            }
        }
    }
}

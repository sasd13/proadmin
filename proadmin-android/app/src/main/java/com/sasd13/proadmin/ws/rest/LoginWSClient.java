package com.sasd13.proadmin.ws.rest;

import com.sasd13.javaex.net.http.HttpHeader;
import com.sasd13.javaex.net.http.HttpRequest;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.util.MediaType;
import com.sasd13.proadmin.util.Parameter;
import com.sasd13.proadmin.ws.WSConstants;
import com.sasd13.proadmin.ws.WSInformation;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginWSClient {

    private HttpRequest httpRequest;
    private int timeOut;
    private int statusCode;

    public LoginWSClient() {
        this(WSConstants.TIMEOUT_LOGIN);
    }

    public LoginWSClient(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public long logIn(String number, String password) {
        long id = 0;

        MediaType reqMediaType = MediaType.APPLICATION_JSON;

        try {
            Map<String, String> logins = new HashMap<>();
            logins.put(Parameter.NUMBER.getName(), number);
            logins.put(Parameter.PASSWORD.getName(), password);

            String reqData = ParserFactory.make(reqMediaType).toString(logins);

            httpRequest = new HttpRequest(HttpRequest.HttpMethod.POST, new URL(WSInformation.URL_LOGIN));
            httpRequest.open(timeOut);
            httpRequest.setOutPutEnabled(true);
            httpRequest.addHeader(HttpHeader.CONTENT_TYPE_FIELD.getName(), reqMediaType.getMIMEType());
            httpRequest.addHeader(HttpHeader.REQUEST_PARAMETERIZED_FIELD.getName(), HttpHeader.REQUEST_PARAMETERIZED_VALUE_YES.getName());
            setRequestHeaderAccept();
            httpRequest.connect();
            httpRequest.writeRequestData(reqData);

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            MediaType respMediaType = MediaType.find(respContentType);
            id = (long) ParserFactory.make(respMediaType).fromString(respData, Long.class);
        } catch (ParserException | IOException e) {
            e.printStackTrace();
        }

        return id;
    }

    private void setRequestHeaderAccept() {
        httpRequest.addHeader(HttpHeader.ACCEPT_FIELD.getName(), MediaType.APPLICATION_JSON.getMIMEType());
        httpRequest.addHeader(HttpHeader.ACCEPT_FIELD.getName(), MediaType.APPLICATION_XML.getMIMEType());
    }
}

package com.sasd13.proadmin.ws.rest;

import com.sasd13.javaex.net.http.EnumHttpHeaderField;
import com.sasd13.javaex.net.http.EnumHttpHeaderValue;
import com.sasd13.javaex.net.http.HttpRequest;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.util.EnumMediaType;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws.WSConstants;
import com.sasd13.proadmin.ws.WSInformation;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LogInWSClient {

    private HttpRequest httpRequest;
    private int timeOut;
    private int statusCode;

    public LogInWSClient() {
        this(WSConstants.TIMEOUT_LOGIN);
    }

    public LogInWSClient(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public long logIn(String number, String password) {
        long id = 0;

        EnumMediaType reqMediaType = EnumMediaType.APPLICATION_JSON;

        try {
            Map<String, String> logins = new HashMap<>();
            logins.put(EnumParameter.NUMBER.getName(), number);
            logins.put(EnumParameter.PASSWORD.getName(), password);

            String reqData = ParserFactory.make(reqMediaType).toString(logins);

            httpRequest = new HttpRequest(HttpRequest.EnumMethod.POST, new URL(WSInformation.URL_LOGIN));
            httpRequest.open(timeOut);
            httpRequest.setOutPutEnabled(true);
            httpRequest.addHeader(EnumHttpHeaderField.CONTENT_TYPE.getName(), reqMediaType.getMIMEType());
            httpRequest.addHeader(EnumHttpHeaderField.REQUEST_PARAMETERIZED.getName(), EnumHttpHeaderValue.REQUEST_PARAMETERIZED_YES.getName());
            setRequestHeaderAccept();
            httpRequest.connect();
            httpRequest.writeRequestData(reqData);

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            EnumMediaType respMediaType = EnumMediaType.find(respContentType);
            id = (long) ParserFactory.make(respMediaType).fromString(respData, Long.class);
        } catch (ParserException | IOException e) {
            e.printStackTrace();
        }

        return id;
    }

    private void setRequestHeaderAccept() {
        httpRequest.addHeader(EnumHttpHeaderField.ACCEPT.getName(), EnumMediaType.APPLICATION_JSON.getMIMEType());
        httpRequest.addHeader(EnumHttpHeaderField.ACCEPT.getName(), EnumMediaType.APPLICATION_XML.getMIMEType());
    }
}

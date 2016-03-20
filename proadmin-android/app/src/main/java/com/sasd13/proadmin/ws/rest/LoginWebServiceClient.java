package com.sasd13.proadmin.ws.rest;

import com.sasd13.javaex.net.http.HttpHeader;
import com.sasd13.javaex.net.http.HttpRequest;
import com.sasd13.javaex.net.http.MediaType;
import com.sasd13.javaex.util.DataParser;
import com.sasd13.javaex.util.DataParserException;
import com.sasd13.proadmin.util.Parameter;
import com.sasd13.proadmin.ws.WebServiceInformation;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginWebServiceClient {

    public static final int DEFAULT_TIMEOUT = 60000;
    public static final int STATUS_OK = HttpURLConnection.HTTP_OK;

    private HttpRequest httpRequest;
    private int timeOut;
    private int statusCode;

    public LoginWebServiceClient(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public long logIn(String number, String hash) {
        long id = 0;

        String reqContentType = MediaType.APPLICATION_JSON.getMIMEType();

        try {
            Map<String, String> logins = new HashMap<>();
            logins.put(Parameter.NUMBER.getName(), number);
            logins.put(Parameter.PASSWORD.getName(), hash);

            String reqData = DataParser.toString(reqContentType, logins);

            httpRequest = new HttpRequest(new URL(WebServiceInformation.URL_LOGIN), HttpRequest.HttpMethod.POST);
            httpRequest.open(timeOut);
            httpRequest.setOutPutEnabled(true);
            httpRequest.addHeader(HttpHeader.CONTENT_TYPE_FIELD.getName(), reqContentType);
            httpRequest.addHeader(HttpHeader.REQUEST_PARAMETERIZED_FIELD.getName(), HttpHeader.REQUEST_PARAMETERIZED_VALUE_YES.getName());
            setRequestHeaderAccept();
            httpRequest.connect();
            httpRequest.writeRequestData(reqData);

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            id = (long) DataParser.fromString(respContentType, respData, Long.class);
        } catch (DataParserException | IOException e) {
            e.printStackTrace();
        }

        return id;
    }

    private void setRequestHeaderAccept() {
        httpRequest.addHeader(HttpHeader.ACCEPT_FIELD.getName(), MediaType.APPLICATION_JSON.getMIMEType());
        httpRequest.addHeader(HttpHeader.ACCEPT_FIELD.getName(), MediaType.APPLICATION_XML.getMIMEType());
    }
}

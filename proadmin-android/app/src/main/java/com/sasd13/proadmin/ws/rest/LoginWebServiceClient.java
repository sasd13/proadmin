package com.sasd13.proadmin.ws.rest;

import com.sasd13.javaex.net.http.HttpHeader;
import com.sasd13.javaex.net.http.HttpRequest;
import com.sasd13.javaex.net.http.MediaType;
import com.sasd13.javaex.util.DataParser;
import com.sasd13.javaex.util.DataParserException;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.ws.WebServiceInformation;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginWebServiceClient {

    private static final int DEFAULT_TIMEOUT = 60000;
    public static final int STATUS_OK = HttpURLConnection.HTTP_OK;

    private Teacher teacher;
    private HttpRequest httpRequest;
    private int timeOut;
    private int statusCode;

    public LoginWebServiceClient() {
        this(DEFAULT_TIMEOUT);
    }

    public LoginWebServiceClient(int timeOut) {
        this.timeOut = timeOut;
        teacher = new Teacher();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public long login(String number, String password) {
        long id = 0;

        teacher.setNumber(number);
        teacher.setPassword(password);

        String reqContentType = MediaType.APPLICATION_JSON.getMIMEType();

        try {
            String reqData = DataParser.toString(reqContentType, teacher);

            httpRequest = new HttpRequest(new URL(WebServiceInformation.URL_LOGIN), HttpRequest.HttpMethod.POST);
            httpRequest.open(timeOut);
            httpRequest.setOutPutEnabled(true);
            httpRequest.addHeader(HttpHeader.CONTENT_TYPE_FIELD.getName(), reqContentType);
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

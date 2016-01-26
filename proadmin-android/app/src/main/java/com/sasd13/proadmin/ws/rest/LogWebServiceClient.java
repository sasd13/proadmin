package com.sasd13.proadmin.ws.rest;

import com.sasd13.javaex.net.MimeType;
import com.sasd13.javaex.net.http.HttpRequest;
import com.sasd13.javaex.net.parser.DataParser;
import com.sasd13.proadmin.core.bean.member.Teacher;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LogWebServiceClient {

    private static final String URL_LOGINWEBSERVICE = "http://192.168.1.9:8080/proadmin-ws/login";
    private static final int DEFAULT_TIMEOUT = 60000;
    public static final int STATUS_OK = HttpURLConnection.HTTP_OK;

    private Teacher teacher;
    private HttpRequest httpRequest;
    private int timeOut;
    private int statusCode;

    public LogWebServiceClient() {
        this(DEFAULT_TIMEOUT);
    }

    public LogWebServiceClient(int timeOut) {
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

        String reqContentType = MimeType.APPLICATION_JSON;
        String reqData = DataParser.toString(reqContentType, teacher);

        try {
            httpRequest = new HttpRequest(new URL(URL_LOGINWEBSERVICE), HttpRequest.Method.POST);
            httpRequest.open(timeOut);
            httpRequest.setOutPutEnabled(true);
            httpRequest.setRequestContentType(reqContentType);
            setHttpRequestHeaders();
            httpRequest.connect();
            httpRequest.writeRequestData(reqData);

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            id = (long) DataParser.fromString(respContentType, respData, Long.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return id;
    }

    private void setHttpRequestHeaders() {
        httpRequest.addRequestHeader("Accept", MimeType.APPLICATION_JSON);
        httpRequest.addRequestHeader("Accept", MimeType.APPLICATION_XML);
    }
}

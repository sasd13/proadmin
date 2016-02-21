package com.sasd13.proadmin.ws.rest;

import com.sasd13.javaex.net.MimeType;
import com.sasd13.javaex.net.http.HttpException;
import com.sasd13.javaex.net.http.HttpRequest;
import com.sasd13.javaex.net.ws.DataSerializer;
import com.sasd13.javaex.net.ws.DataSerializerException;
import com.sasd13.proadmin.core.bean.member.Teacher;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

public class LogWebServiceClient {

    private static final String URL_WEBSERVICE_LOGIN = "http://192.168.1.9:8080/proadmin-ws/login";
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

        try {
            String reqData = DataSerializer.toString(reqContentType, teacher);

            httpRequest = new HttpRequest(new URL(URL_WEBSERVICE_LOGIN), HttpRequest.Method.POST);
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

            id = (long) DataSerializer.fromString(respContentType, respData, Long.class);
        } catch (DataSerializerException | IOException | HttpException | URISyntaxException e) {
            e.printStackTrace();
        }

        return id;
    }

    private void setHttpRequestHeaders() {
        httpRequest.addRequestHeader(HttpRequest.HEADER_ATTRIBUTE_ACCEPT, MimeType.APPLICATION_JSON);
        httpRequest.addRequestHeader(HttpRequest.HEADER_ATTRIBUTE_ACCEPT, MimeType.APPLICATION_XML);
    }
}

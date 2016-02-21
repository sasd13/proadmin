package com.sasd13.proadmin.ws.rest;

import com.sasd13.javaex.net.MimeType;
import com.sasd13.javaex.net.http.HttpException;
import com.sasd13.javaex.net.http.HttpRequest;
import com.sasd13.javaex.net.http.util.URLParameterEncoder;
import com.sasd13.javaex.net.ws.DataSerializer;
import com.sasd13.javaex.net.ws.DataSerializerException;
import com.sasd13.javaex.net.ws.rest.IWebServiceClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public class WebServiceClient<T> implements IWebServiceClient<T> {

    private static final String URL_WEBSERVICE = "http://192.168.1.9:8080/proadmin-ws/";
    private static final int DEFAULT_TIMEOUT = 60000;
    public static final int STATUS_OK = HttpURLConnection.HTTP_OK;

    private Class<T> mClass;
    private int timeOut;
    private String url;
    private HttpRequest httpRequest;
    private int statusCode;

    public WebServiceClient(Class<T> mClass) {
        this(mClass, DEFAULT_TIMEOUT);
    }

    public WebServiceClient(Class<T> mClass, int timeOut) {
        this.mClass = mClass;
        url = URL_WEBSERVICE + mClass.getSimpleName().toLowerCase() + "s";
        this.timeOut = timeOut;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public T get(long id) {
        T t = null;

        String urlParams = "?id=" + id;

        try {
            httpRequest = new HttpRequest(new URL(url + urlParams), HttpRequest.Method.GET);
            httpRequest.open(timeOut);
            setHttpRequestHeaders();
            httpRequest.connect();

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            t = (T) DataSerializer.fromString(respContentType, respData, mClass);
        } catch (IOException | HttpException | URISyntaxException | DataSerializerException | ClassCastException e) {
            e.printStackTrace();
        }

        return t;
    }

    private void setHttpRequestHeaders() {
        httpRequest.addRequestHeader(HttpRequest.HEADER_ATTRIBUTE_ACCEPT, MimeType.APPLICATION_JSON);
        httpRequest.addRequestHeader(HttpRequest.HEADER_ATTRIBUTE_ACCEPT, MimeType.APPLICATION_XML);
    }

    @Override
    public T[] getAll() {
        T[] ts = null;

        try {
            httpRequest = new HttpRequest(new URL(url), HttpRequest.Method.GET);
            httpRequest.open(timeOut);
            setHttpRequestHeaders();
            httpRequest.connect();

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            ts = (T[]) DataSerializer.fromString(respContentType, respData, mClass);
        } catch (IOException | HttpException | URISyntaxException | DataSerializerException | ClassCastException e) {
            e.printStackTrace();
        }

        return ts;
    }

    public T[] getAll(Map<String, String[]> parameters) {
        T[] ts = null;

        try {
            String urlParams = URLParameterEncoder.toEncodedURLString(parameters);

            httpRequest = new HttpRequest(new URL(url + urlParams), HttpRequest.Method.GET);
            httpRequest.open(timeOut);
            setHttpRequestHeaders();
            httpRequest.connect();

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            ts = (T[]) DataSerializer.fromString(respContentType, respData, mClass);
        } catch (IOException | HttpException | URISyntaxException | DataSerializerException | ClassCastException e) {
            e.printStackTrace();
        }

        return ts;
    }

    @Override
    public long post(T t) {
        long id = 0;

        String reqContentType = MimeType.APPLICATION_JSON;

        try {
            String reqData = DataSerializer.toString(reqContentType, t);

            httpRequest = new HttpRequest(new URL(url), HttpRequest.Method.POST);
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

    @Override
    public void put(T t) {
        String reqContentType = MimeType.APPLICATION_JSON;

        try {
            String reqData = DataSerializer.toString(reqContentType, t);

            httpRequest = new HttpRequest(new URL(url), HttpRequest.Method.PUT);
            httpRequest.open(timeOut);
            httpRequest.setOutPutEnabled(true);
            httpRequest.setRequestContentType(reqContentType);
            httpRequest.connect();
            httpRequest.writeRequestData(reqData);

            statusCode = httpRequest.getResponseCode();

            httpRequest.disconnect();
        } catch (DataSerializerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putAll(T[] ts) {
        String reqContentType = MimeType.APPLICATION_JSON;

        try {
            String reqData = DataSerializer.toString(reqContentType, ts);

            httpRequest = new HttpRequest(new URL(url), HttpRequest.Method.PUT);
            httpRequest.open(timeOut);
            httpRequest.setOutPutEnabled(true);
            httpRequest.setRequestContentType(reqContentType);
            httpRequest.connect();
            httpRequest.writeRequestData(reqData);

            statusCode = httpRequest.getResponseCode();

            httpRequest.disconnect();
        } catch (DataSerializerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String urlParams = "?id=" + id;

        try {
            httpRequest = new HttpRequest(new URL(url + urlParams), HttpRequest.Method.DELETE);
            httpRequest.open(timeOut);
            httpRequest.connect();

            statusCode = httpRequest.getResponseCode();

            httpRequest.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        //Do nothing
    }
}

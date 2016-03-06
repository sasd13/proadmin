package com.sasd13.proadmin.ws.rest;

import com.sasd13.javaex.net.http.HttpHeader;
import com.sasd13.javaex.net.http.HttpRequest;
import com.sasd13.javaex.net.http.MediaType;
import com.sasd13.javaex.net.util.URLParameterEncoder;
import com.sasd13.javaex.net.ws.rest.IWebServiceClient;
import com.sasd13.javaex.util.DataParser;
import com.sasd13.javaex.util.DataParserException;
import com.sasd13.proadmin.ws.WebServiceInformation;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class WebServiceClient<T> implements IWebServiceClient<T> {

    private static final int DEFAULT_TIMEOUT = 60000;
    public static final int STATUS_OK = HttpURLConnection.HTTP_OK;

    private Class<T> mClass;
    private int timeOut;
    private String url;
    private HttpRequest httpRequest;
    private int statusCode;
    private boolean dataRetrieveDeepEnabled;

    public WebServiceClient(Class<T> mClass) {
        this(mClass, DEFAULT_TIMEOUT);
    }

    public WebServiceClient(Class<T> mClass, int timeOut) {
        this.mClass = mClass;
        url = WebServiceInformation.URL + mClass.getSimpleName().toLowerCase() + "s";
        this.timeOut = timeOut;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setDataRetrieveDeepEnabled(boolean dataRetrieveDeepEnabled) {
        this.dataRetrieveDeepEnabled = dataRetrieveDeepEnabled;
    }

    @Override
    public T get(long id) {
        T t = null;

        String urlParams = "?id=" + id;

        try {
            httpRequest = new HttpRequest(new URL(url + urlParams), HttpRequest.HttpMethod.GET);
            httpRequest.open(timeOut);
            setRequestHeaderAccept();
            setRequestHeaderDataRetrieve();
            httpRequest.connect();

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            t = (T) DataParser.fromString(respContentType, respData, mClass);
        } catch (IOException | DataParserException e) {
            e.printStackTrace();
        }

        return t;
    }

    private void setRequestHeaderAccept() {
        httpRequest.addHeader(HttpHeader.ACCEPT_FIELD.getName(), MediaType.APPLICATION_JSON.getMIMEType());
        httpRequest.addHeader(HttpHeader.ACCEPT_FIELD.getName(), MediaType.APPLICATION_XML.getMIMEType());
    }

    private void setRequestHeaderDataRetrieve() {
        if (dataRetrieveDeepEnabled) {
            httpRequest.addHeader(HttpHeader.DATA_RETRIEVE_FIELD.getName(), HttpHeader.DATA_RETRIEVE_VALUE_DEEP.getName());
        }
    }

    public T[] get(Map<String, String[]> parameters) {
        T[] ts = null;

        try {
            String urlParams = URLParameterEncoder.toEncodedURLString(parameters);

            httpRequest = new HttpRequest(new URL(url + urlParams), HttpRequest.HttpMethod.GET);
            httpRequest.open(timeOut);
            setRequestHeaderAccept();
            setRequestHeaderDataRetrieve();
            httpRequest.connect();

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            ts = (T[]) DataParser.fromString(respContentType, respData, mClass);
        } catch (IOException | DataParserException e) {
            e.printStackTrace();
        }

        return ts;
    }

    @Override
    public T[] getAll() {
        T[] ts = null;

        try {
            httpRequest = new HttpRequest(new URL(url), HttpRequest.HttpMethod.GET);
            httpRequest.open(timeOut);
            setRequestHeaderAccept();
            setRequestHeaderDataRetrieve();
            httpRequest.connect();

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            ts = (T[]) DataParser.fromString(respContentType, respData, mClass);
        } catch (IOException | DataParserException e) {
            e.printStackTrace();
        }

        return ts;
    }

    @Override
    public long post(T t) {
        long id = 0;

        String reqContentType = MediaType.APPLICATION_JSON.getMIMEType();

        try {
            String reqData = DataParser.toString(reqContentType, t);

            httpRequest = new HttpRequest(new URL(url), HttpRequest.HttpMethod.POST);
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

    @Override
    public void put(T t) {
        String reqContentType = MediaType.APPLICATION_JSON.getMIMEType();

        try {
            String reqData = DataParser.toString(reqContentType, t);

            httpRequest = new HttpRequest(new URL(url), HttpRequest.HttpMethod.PUT);
            httpRequest.open(timeOut);
            httpRequest.setOutPutEnabled(true);
            httpRequest.addHeader(HttpHeader.CONTENT_TYPE_FIELD.getName(), reqContentType);
            httpRequest.connect();
            httpRequest.writeRequestData(reqData);

            statusCode = httpRequest.getResponseCode();

            httpRequest.disconnect();
        } catch (DataParserException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putAll(T[] ts) {
        String reqContentType = MediaType.APPLICATION_JSON.getMIMEType();

        try {
            String reqData = DataParser.toString(reqContentType, ts);

            httpRequest = new HttpRequest(new URL(url), HttpRequest.HttpMethod.PUT);
            httpRequest.open(timeOut);
            httpRequest.setOutPutEnabled(true);
            httpRequest.addHeader(HttpHeader.CONTENT_TYPE_FIELD.getName(), reqContentType);
            httpRequest.connect();
            httpRequest.writeRequestData(reqData);

            statusCode = httpRequest.getResponseCode();

            httpRequest.disconnect();
        } catch (DataParserException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String urlParams = "?id=" + id;

        try {
            httpRequest = new HttpRequest(new URL(url + urlParams), HttpRequest.HttpMethod.DELETE);
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

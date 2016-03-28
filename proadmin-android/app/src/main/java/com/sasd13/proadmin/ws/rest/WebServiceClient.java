package com.sasd13.proadmin.ws.rest;

import com.sasd13.javaex.net.http.HttpHeader;
import com.sasd13.javaex.net.http.HttpRequest;
import com.sasd13.javaex.net.util.URLParameterEncoder;
import com.sasd13.javaex.net.ws.rest.IWebServiceClient;
import com.sasd13.javaex.util.DataParser;
import com.sasd13.javaex.util.DataParserException;
import com.sasd13.javaex.util.MediaType;
import com.sasd13.proadmin.ws.WebServiceInformation;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WebServiceClient<T> implements IWebServiceClient<T> {

    public static final int DEFAULT_TIMEOUT = 60000;
    public static final int STATUS_OK = HttpURLConnection.HTTP_OK;

    private Class<T> mClass;
    private int timeOut;
    private String url;
    private HttpRequest httpRequest;
    private int statusCode;
    private boolean dataRetrieveDeepEnabled;

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
            httpRequest = new HttpRequest(HttpRequest.HttpMethod.GET, new URL(url + urlParams));
            httpRequest.open(timeOut);
            setRequestHeaderAccept();
            setRequestHeaderDataRetrieve();
            httpRequest.connect();

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            t = (T) DataParser.fromString(respData, MediaType.find(respContentType), mClass);
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

    public List<T> get(Map<String, String[]> parameters) {
        List<T> ts = new ArrayList<>();

        try {
            String urlParams = URLParameterEncoder.toEncodedURLString(parameters);

            httpRequest = new HttpRequest(HttpRequest.HttpMethod.GET, new URL(url + urlParams));
            httpRequest.open(timeOut);
            httpRequest.addHeader(HttpHeader.REQUEST_PARAMETERIZED_FIELD.getName(), HttpHeader.REQUEST_PARAMETERIZED_VALUE_YES.getName());
            setRequestHeaderAccept();
            setRequestHeaderDataRetrieve();
            httpRequest.connect();

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            Collections.addAll(ts, (T[]) DataParser.fromString(respData, MediaType.find(respContentType), mClass));
        } catch (IOException | DataParserException e) {
            e.printStackTrace();
        }

        return ts;
    }

    @Override
    public List<T> getAll() {
        List<T> ts = new ArrayList<>();

        try {
            httpRequest = new HttpRequest(HttpRequest.HttpMethod.GET, new URL(url));
            httpRequest.open(timeOut);
            setRequestHeaderAccept();
            setRequestHeaderDataRetrieve();
            httpRequest.connect();

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            Collections.addAll(ts, (T[]) DataParser.fromString(respData, MediaType.find(respContentType), mClass));
        } catch (IOException | DataParserException e) {
            e.printStackTrace();
        }

        return ts;
    }

    @Override
    public long post(T t) {
        long id = 0;

        MediaType reqMediaType = MediaType.APPLICATION_JSON;

        try {
            String reqData = DataParser.toString(t, reqMediaType);

            httpRequest = new HttpRequest(HttpRequest.HttpMethod.POST, new URL(url));
            httpRequest.open(timeOut);
            httpRequest.setOutPutEnabled(true);
            httpRequest.addHeader(HttpHeader.CONTENT_TYPE_FIELD.getName(), reqMediaType.getMIMEType());
            setRequestHeaderAccept();
            httpRequest.connect();
            httpRequest.writeRequestData(reqData);

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            id = (long) DataParser.fromString(respData, MediaType.find(respContentType), Long.class);
        } catch (DataParserException | IOException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public void put(T t) {
        MediaType reqMediaType = MediaType.APPLICATION_JSON;

        try {
            String reqData = DataParser.toString(t, reqMediaType);

            httpRequest = new HttpRequest(HttpRequest.HttpMethod.PUT, new URL(url));
            httpRequest.open(timeOut);
            httpRequest.setOutPutEnabled(true);
            httpRequest.addHeader(HttpHeader.CONTENT_TYPE_FIELD.getName(), reqMediaType.getMIMEType());
            httpRequest.connect();
            httpRequest.writeRequestData(reqData);

            statusCode = httpRequest.getResponseCode();

            httpRequest.disconnect();
        } catch (DataParserException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putAll(List<T> ts) {
        MediaType reqMediaType = MediaType.APPLICATION_JSON;

        try {
            String reqData = DataParser.toString(ts.toArray((T[]) Array.newInstance(mClass, ts.size())), reqMediaType);

            httpRequest = new HttpRequest(HttpRequest.HttpMethod.PUT, new URL(url));
            httpRequest.open(timeOut);
            httpRequest.setOutPutEnabled(true);
            httpRequest.addHeader(HttpHeader.CONTENT_TYPE_FIELD.getName(), reqMediaType.getMIMEType());
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
            httpRequest = new HttpRequest(HttpRequest.HttpMethod.DELETE, new URL(url + urlParams));
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

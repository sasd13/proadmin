package com.sasd13.proadmin.ws.rest;

import com.sasd13.javaex.net.http.HttpHeader;
import com.sasd13.javaex.net.http.HttpRequest;
import com.sasd13.javaex.net.util.URLParameterEncoder;
import com.sasd13.javaex.net.ws.rest.IWebServiceClient;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.util.MediaType;
import com.sasd13.proadmin.ws.WSConstants;
import com.sasd13.proadmin.ws.WSInformation;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WSClient<T> implements IWebServiceClient<T> {

    private Class<T> mClass;
    private int timeOut;
    private String url;
    private HttpRequest httpRequest;
    private int statusCode;
    private boolean dataRetrieveDeepEnabled;

    public WSClient(Class<T> mClass) {
        this(mClass, WSConstants.TIMEOUT);
    }

    public WSClient(Class<T> mClass, int timeOut) {
        this.mClass = mClass;
        url = WSInformation.URL + mClass.getSimpleName().toLowerCase() + "s";
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

            MediaType respMediType = MediaType.find(respContentType);

            t = (T) ParserFactory.make(respMediType).fromString(respData, mClass);
        } catch (IOException | ParserException e) {
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

            MediaType respMediType = MediaType.find(respContentType);

            Collections.addAll(ts, (T[]) ParserFactory.make(respMediType).fromString(respData, mClass));
        } catch (IOException | ParserException e) {
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

            MediaType respMediType = MediaType.find(respContentType);

            Collections.addAll(ts, (T[]) ParserFactory.make(respMediType).fromString(respData, mClass));
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }

        return ts;
    }

    @Override
    public long post(T t) {
        long id = 0;

        MediaType reqMediaType = MediaType.APPLICATION_JSON;

        try {
            String reqData = ParserFactory.make(reqMediaType).toString(t);

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

            MediaType respMediType = MediaType.find(respContentType);

            id = (long) ParserFactory.make(respMediType).fromString(respData, Long.class);
        } catch (ParserException | IOException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public void put(T t) {
        MediaType reqMediaType = MediaType.APPLICATION_JSON;

        try {
            String reqData = ParserFactory.make(reqMediaType).toString(t);

            httpRequest = new HttpRequest(HttpRequest.HttpMethod.PUT, new URL(url));
            httpRequest.open(timeOut);
            httpRequest.setOutPutEnabled(true);
            httpRequest.addHeader(HttpHeader.CONTENT_TYPE_FIELD.getName(), reqMediaType.getMIMEType());
            httpRequest.connect();
            httpRequest.writeRequestData(reqData);

            statusCode = httpRequest.getResponseCode();

            httpRequest.disconnect();
        } catch (ParserException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putAll(List<T> ts) {
        MediaType reqMediaType = MediaType.APPLICATION_JSON;

        try {
            String reqData = ParserFactory.make(reqMediaType).toString(ts.toArray((T[]) Array.newInstance(mClass, ts.size())));

            httpRequest = new HttpRequest(HttpRequest.HttpMethod.PUT, new URL(url));
            httpRequest.open(timeOut);
            httpRequest.setOutPutEnabled(true);
            httpRequest.addHeader(HttpHeader.CONTENT_TYPE_FIELD.getName(), reqMediaType.getMIMEType());
            httpRequest.connect();
            httpRequest.writeRequestData(reqData);

            statusCode = httpRequest.getResponseCode();

            httpRequest.disconnect();
        } catch (ParserException | IOException e) {
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

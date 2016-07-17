package com.sasd13.proadmin.ws.rest;

import com.sasd13.javaex.net.http.EnumHttpHeaderField;
import com.sasd13.javaex.net.http.EnumHttpHeaderValue;
import com.sasd13.javaex.net.http.HttpRequest;
import com.sasd13.javaex.net.util.URLQueryEncoder;
import com.sasd13.javaex.net.ws.rest.IWebServiceClient;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.util.EnumMediaType;
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
            httpRequest = new HttpRequest(HttpRequest.EnumMethod.GET, new URL(url + urlParams));
            httpRequest.open(timeOut);
            setRequestHeaderAccept();
            setRequestHeaderDataRetrieve();
            httpRequest.connect();

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            EnumMediaType respMediType = EnumMediaType.find(respContentType);

            t = ParserFactory.make(respMediType).fromString(respData, mClass);
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }

        return t;
    }

    private void setRequestHeaderAccept() {
        httpRequest.addHeader(EnumHttpHeaderField.ACCEPT.getName(), EnumMediaType.APPLICATION_JSON.getMIMEType());
        httpRequest.addHeader(EnumHttpHeaderField.ACCEPT.getName(), EnumMediaType.APPLICATION_XML.getMIMEType());
    }

    private void setRequestHeaderDataRetrieve() {
        if (dataRetrieveDeepEnabled) {
            httpRequest.addHeader(EnumHttpHeaderField.DATA_RETRIEVE.getName(), EnumHttpHeaderValue.DATA_RETRIEVE_DEEP.getName());
        }
    }

    public List<T> get(Map<String, String[]> parameters) {
        List<T> ts = new ArrayList<>();

        try {
            String urlParams = URLQueryEncoder.toEncodedURLString(parameters);

            httpRequest = new HttpRequest(HttpRequest.EnumMethod.GET, new URL(url + urlParams));
            httpRequest.open(timeOut);
            httpRequest.addHeader(EnumHttpHeaderField.REQUEST_PARAMETERIZED.getName(), EnumHttpHeaderValue.REQUEST_PARAMETERIZED_YES.getName());
            setRequestHeaderAccept();
            setRequestHeaderDataRetrieve();
            httpRequest.connect();

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            EnumMediaType respMediType = EnumMediaType.find(respContentType);

            Collections.addAll(ts, ParserFactory.make(respMediType).fromStringArray(respData, mClass));
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }

        return ts;
    }

    @Override
    public List<T> getAll() {
        List<T> ts = new ArrayList<>();

        try {
            httpRequest = new HttpRequest(HttpRequest.EnumMethod.GET, new URL(url));
            httpRequest.open(timeOut);
            setRequestHeaderAccept();
            setRequestHeaderDataRetrieve();
            httpRequest.connect();

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            EnumMediaType respMediType = EnumMediaType.find(respContentType);

            Collections.addAll(ts, ParserFactory.make(respMediType).fromStringArray(respData, mClass));
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }

        return ts;
    }

    @Override
    public long post(T t) {
        long id = 0;

        EnumMediaType reqEnumMediaType = EnumMediaType.APPLICATION_JSON;

        try {
            String reqData = ParserFactory.make(reqEnumMediaType).toString(t);

            httpRequest = new HttpRequest(HttpRequest.EnumMethod.POST, new URL(url));
            httpRequest.open(timeOut);
            httpRequest.setOutPutEnabled(true);
            httpRequest.addHeader(EnumHttpHeaderField.CONTENT_TYPE.getName(), reqEnumMediaType.getMIMEType());
            setRequestHeaderAccept();
            httpRequest.connect();
            httpRequest.writeRequestData(reqData);

            statusCode = httpRequest.getResponseCode();

            String respContentType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            httpRequest.disconnect();

            EnumMediaType respMediType = EnumMediaType.find(respContentType);

            id = ParserFactory.make(respMediType).fromString(respData, Long.class);
        } catch (ParserException | IOException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public void put(T t) {
        EnumMediaType reqEnumMediaType = EnumMediaType.APPLICATION_JSON;

        try {
            String reqData = ParserFactory.make(reqEnumMediaType).toString(t);

            httpRequest = new HttpRequest(HttpRequest.EnumMethod.PUT, new URL(url));
            httpRequest.open(timeOut);
            httpRequest.setOutPutEnabled(true);
            httpRequest.addHeader(EnumHttpHeaderField.CONTENT_TYPE.getName(), reqEnumMediaType.getMIMEType());
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
        EnumMediaType reqEnumMediaType = EnumMediaType.APPLICATION_JSON;

        try {
            String reqData = ParserFactory.make(reqEnumMediaType).toString(ts.toArray((T[]) Array.newInstance(mClass, ts.size())));

            httpRequest = new HttpRequest(HttpRequest.EnumMethod.PUT, new URL(url));
            httpRequest.open(timeOut);
            httpRequest.setOutPutEnabled(true);
            httpRequest.addHeader(EnumHttpHeaderField.CONTENT_TYPE.getName(), reqEnumMediaType.getMIMEType());
            httpRequest.addHeader(EnumHttpHeaderField.DATA_COLLECTION.getName(), EnumHttpHeaderValue.DATA_COLLECTION_YES.getName());
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
            httpRequest = new HttpRequest(HttpRequest.EnumMethod.DELETE, new URL(url + urlParams));
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

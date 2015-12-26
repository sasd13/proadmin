package com.sasd13.proadmin.ws.rest;

import com.sasd13.javaex.net.HttpRequest;
import com.sasd13.javaex.net.MimeType;
import com.sasd13.javaex.net.parser.DataParser;
import com.sasd13.javaex.net.parser.ParametersParser;
import com.sasd13.javaex.net.ws.rest.IWebServiceClient;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class WebServiceClient<T> implements IWebServiceClient<T> {

    private static final String URL_WEBSERVICE = "http://192.168.1.9:8080/proadmin-ws/";
    private static final int DEFAULT_TIMEOUT = 60000;

    protected Class<T> mClass;
    protected int timeOut;
    protected String url;
    protected HttpRequest httpRequest;

    public WebServiceClient(Class<T> mClass) {
        this.mClass = mClass;
        url = URL_WEBSERVICE + mClass.getSimpleName().toLowerCase() + "s";
        timeOut = DEFAULT_TIMEOUT;
    }

    public WebServiceClient(Class<T> mClass, int timeOut) {
        this(mClass);

        this.timeOut = timeOut;
    }

    @Override
    public T get(long id) {
        T t = null;

        String urlParams = "?id=" + id;

        try {
            httpRequest = new HttpRequest(new URL(url + urlParams));
            httpRequest.open(timeOut);
            httpRequest.connect();

            String mimeType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            t = (T) DataParser.decode(mimeType, respData, mClass);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return t;
    }

    public T get(Map<String, String[]> parameters) {
        T[] ts = getAll(parameters);

        return (ts != null) ? ts[0] : null;
    }

    @Override
    public T[] getAll() {
        T[] ts = null;

        try {
            httpRequest = new HttpRequest(new URL(url));
            httpRequest.open(timeOut);
            httpRequest.connect();

            String mimeType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            ts = (T[]) DataParser.decode(mimeType, respData, mClass);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ts;
    }

    public T[] getAll(Map<String, String[]> parameters) {
        T[] ts = null;

        String urlParams = ParametersParser.toEncodedURLString(parameters);

        try {
            httpRequest = new HttpRequest(new URL(url + urlParams));
            httpRequest.open(timeOut);
            httpRequest.connect();

            String mimeType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            ts = (T[]) DataParser.decode(mimeType, respData, mClass);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ts;
    }

    @Override
    public long post(T t) {
        long id = 0;

        String reqData = DataParser.encode(MimeType.APPLICATION_JSON, t);

        try {
            httpRequest = new HttpRequest(new URL(url), "POST");
            httpRequest.open(timeOut);
            httpRequest.connect();
            httpRequest.writeRequestData(MimeType.APPLICATION_JSON, reqData);

            String mimeType = httpRequest.getResponseContentType();
            String respData = httpRequest.readResponseData();

            id = (long) DataParser.decode(mimeType, respData, Long.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public void put(T t) {
        String reqData = DataParser.encode(MimeType.APPLICATION_JSON, t);

        try {
            httpRequest = new HttpRequest(new URL(url), "PUT");
            httpRequest.open(timeOut);
            httpRequest.connect();
            httpRequest.writeRequestData(MimeType.APPLICATION_JSON, reqData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putAll(T[] ts) {
        String reqData = DataParser.encode(MimeType.APPLICATION_JSON, ts);

        try {
            httpRequest = new HttpRequest(new URL(url), "PUT");
            httpRequest.open(timeOut);
            httpRequest.connect();
            httpRequest.writeRequestData(MimeType.APPLICATION_JSON, reqData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        if (!"Teacher".equals(mClass.getSimpleName())
                || !"Project".equals(mClass.getSimpleName())
                || !"Student".equals(mClass.getSimpleName())) {
            String urlParams = "?id=" + id;

            try {
                httpRequest = new HttpRequest(new URL(url + urlParams), "DELETE");
                httpRequest.open(timeOut);
                httpRequest.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteAll() {
    }
}

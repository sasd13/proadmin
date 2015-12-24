package com.sasd13.proadmin.ws.rest;

import com.sasd13.javaex.net.HttpRequest;
import com.sasd13.javaex.net.MimeType;
import com.sasd13.javaex.net.parser.DataParser;
import com.sasd13.javaex.net.ws.rest.IWebServiceClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class WebServiceClient<T> implements IWebServiceClient<T> {

    private static final String WEBSERVICE_URL = "http://192.168.1.9:8080/proadmin-ws/";

    protected Class<T> mClass;
    protected String url;
    protected HttpRequest httpRequest;

    public WebServiceClient(Class<T> mClass) {
        this.mClass = mClass;
        url = WEBSERVICE_URL + mClass.getSimpleName().toLowerCase() + "s";
    }

    @Override
    public T get(long id) {
        T t = null;

        String urlParams = "?id=" + id;

        try {
            httpRequest = new HttpRequest(new URL(url + urlParams));
            httpRequest.hasReponseData(true);
            httpRequest.execute();

            String respData = httpRequest.getResponseData();
            String mimeType = httpRequest.getResponseContentType();

            t = (T) DataParser.decode(mimeType, respData, mClass);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return t;
    }

    private String getUrlParams(Map<String, String> mapParams) {
        StringBuilder builder = new StringBuilder();

        int i = 0;
        for (String key : mapParams.keySet()) {
            if (i == 0) {
                builder.append("?");
                i++;
            } else {
                builder.append("&");
            }

            builder.append(key.toLowerCase());
            builder.append("=");
            builder.append(mapParams.get(key));
        }

        return builder.toString();
    }

    @Override
    public T[] getAll() {
        T[] ts = null;

        try {
            httpRequest = new HttpRequest(new URL(url));
            httpRequest.hasReponseData(true);
            httpRequest.execute();

            String respData = httpRequest.getResponseData();
            String mimeType = httpRequest.getResponseContentType();

            ts = (T[]) DataParser.decode(mimeType, respData, mClass);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ts;
    }

    public T[] getAll(Map<String, String> mapParams) {
        T[] ts = null;

        String urlParams = getUrlParams(mapParams);

        try {
            httpRequest = new HttpRequest(new URL(url + urlParams));
            httpRequest.hasReponseData(true);
            httpRequest.execute();

            String respData = httpRequest.getResponseData();
            String mimeType = httpRequest.getResponseContentType();

            ts = (T[]) DataParser.decode(mimeType, respData, mClass);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ts;
    }

    @Override
    public long post(T t) {
        long id = 0;

        try {
            httpRequest = new HttpRequest(new URL(url));
            httpRequest.setRequestMethod("POST");
            httpRequest.setRequestData(DataParser.encode(MimeType.APPLICATION_JSON, t));
            httpRequest.hasReponseData(true);
            httpRequest.execute();

            String respData = httpRequest.getResponseData();
            String mimeType = httpRequest.getResponseContentType();

            id = (long) DataParser.decode(mimeType, respData, Long.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public void put(T t) {
        try {
            httpRequest = new HttpRequest(new URL(url));
            httpRequest.setRequestMethod("PUT");
            httpRequest.setRequestData(DataParser.encode(MimeType.APPLICATION_JSON, t));
            httpRequest.execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putAll(T[] ts) {
        try {
            httpRequest = new HttpRequest(new URL(url));
            httpRequest.setRequestMethod("PUT");
            httpRequest.setRequestData(DataParser.encode(MimeType.APPLICATION_JSON, ts));
            httpRequest.execute();
        } catch (MalformedURLException e) {
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
                httpRequest = new HttpRequest(new URL(url + urlParams));
                httpRequest.setRequestMethod("DELETE");
                httpRequest.hasReponseData(true);
                httpRequest.execute();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteAll() {}
}

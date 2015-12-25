package com.sasd13.proadmin.ws.rest;

import com.sasd13.javaex.net.HttpRequest;
import com.sasd13.javaex.net.MimeType;
import com.sasd13.javaex.net.parser.DataParser;
import com.sasd13.javaex.net.parser.ParametersParser;
import com.sasd13.javaex.net.ws.rest.IWebServiceClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class WebServiceClient<T> implements IWebServiceClient<T> {

    private static final String URL_WEBSERVICE = "http://192.168.1.9:8080/proadmin-ws/";

    protected Class<T> mClass;
    protected String url;
    protected HttpRequest httpRequest;

    public WebServiceClient(Class<T> mClass) {
        this.mClass = mClass;
        url = URL_WEBSERVICE + mClass.getSimpleName().toLowerCase() + "s";
    }

    @Override
    public T get(long id) {
        T t = null;

        String urlParams = "?id=" + id;

        try {
            httpRequest = new HttpRequest(new URL(url + urlParams));
            httpRequest.hasReponseData(true);
            httpRequest.execute();

            String mimeType = httpRequest.getResponseContentType();
            String respData = httpRequest.getResponseData();

            t = (T) DataParser.decode(mimeType, respData, mClass);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return t;
    }

    @Override
    public T[] getAll() {
        T[] ts = null;

        try {
            httpRequest = new HttpRequest(new URL(url));
            httpRequest.hasReponseData(true);
            httpRequest.execute();

            String mimeType = httpRequest.getResponseContentType();
            String respData = httpRequest.getResponseData();

            ts = (T[]) DataParser.decode(mimeType, respData, mClass);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ts;
    }

    public T[] getAll(Map<String, String[]> mapParams) {
        T[] ts = null;

        String urlParams = getUrlParams(mapParams);

        try {
            httpRequest = new HttpRequest(new URL(url + urlParams));
            httpRequest.hasReponseData(true);
            httpRequest.execute();

            String mimeType = httpRequest.getResponseContentType();
            String respData = httpRequest.getResponseData();

            ts = (T[]) DataParser.decode(mimeType, respData, mClass);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ts;
    }

    private String getUrlParams(Map<String, String[]> mapParams) {
        ParametersParser.encode(mapParams);

        StringBuilder builder = new StringBuilder();
        boolean first = true;

        for (String key : mapParams.keySet()) {
            for (String value : mapParams.get(key)) {
                if (first) {
                    builder.append("?");
                    first = false;
                } else {
                    builder.append("&");
                }

                builder.append(key.toLowerCase());
                builder.append("=");
                builder.append(value);
            }
        }

        return builder.toString().trim();
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

            String mimeType = httpRequest.getResponseContentType();
            String respData = httpRequest.getResponseData();

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
                httpRequest.execute();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteAll() {}
}

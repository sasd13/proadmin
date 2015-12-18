package com.sasd13.proadmin.wsclient;

import android.os.AsyncTask;

import com.sasd13.javaex.wsclient.HttpPut;

import java.net.URL;

public class AsyncHttpPut extends AsyncTask<URL, Integer, Void> {

    private String requestData;

    public AsyncHttpPut(String requestData) {
        this.requestData = requestData;
    }

    @Override
    protected Void doInBackground(URL... urls) {
        HttpPut httpPost = new HttpPut(urls[0], this.requestData);
        httpPost.execute();

        return null;
    }
}

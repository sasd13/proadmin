package com.sasd13.proadmin.wsclient;

import android.os.AsyncTask;

import com.sasd13.javaex.wsclient.HttpPost;

import java.net.URL;

public class AsyncHttpPost extends AsyncTask<URL, Integer, String> {

    private String requestData;

    public AsyncHttpPost(String requestData) {
        this.requestData = requestData;
    }

    @Override
    protected String doInBackground(URL... urls) {
        HttpPost httpPost = new HttpPost(urls[0], this.requestData);
        httpPost.execute();

        return httpPost.getResponseData();
    }
}

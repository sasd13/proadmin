package com.sasd13.proadmin.wsclient;

import android.os.AsyncTask;

import com.sasd13.javaex.wsclient.HttpGet;

import java.net.URL;

public class AsyncHttpGet extends AsyncTask<URL, Integer, String> {

    @Override
    protected String doInBackground(URL... urls) {
        HttpGet httpGet = new HttpGet(urls[0]);
        httpGet.execute();

        return httpGet.getResponseData();
    }
}

package com.sasd13.proadmin.wsclient;

import android.os.AsyncTask;

import com.sasd13.javaex.wsclient.HttpPut;

import java.net.URL;

public class AsyncHttpPut extends AsyncTask<URL, Integer, Void> {

    private String contentType;
    private String sData;

    public AsyncHttpPut(String contentType, String sData) {
        this.contentType = contentType;
        this.sData = sData;
    }

    @Override
    protected Void doInBackground(URL... urls) {
        new HttpPut(this.contentType, this.sData).execute(urls[0]);

        return null;
    }
}

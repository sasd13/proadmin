package com.sasd13.proadmin.wsclient;

import android.os.AsyncTask;

import com.sasd13.javaex.wsclient.HttpDelete;

import java.net.URL;

public class AsyncHttpDelete extends AsyncTask<URL, Integer, Void> {

    @Override
    protected Void doInBackground(URL... urls) {
        HttpDelete httpDelete = new HttpDelete(urls[0]);
        httpDelete.execute();

        return null;
    }
}

package com.sasd13.proadmin.wsclient;

import android.os.AsyncTask;

import com.sasd13.javaex.wsclient.HttpDelete;

import java.net.URL;

public class AsyncHttpDelete extends AsyncTask<URL, Integer, Void> {

    @Override
    protected Void doInBackground(URL... urls) {
        new HttpDelete().execute(urls[0]);

        return null;
    }
}

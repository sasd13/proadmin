package com.sasd13.proadmin.wsclient;

import android.os.AsyncTask;

import com.sasd13.javaex.wsclient.HttpGet;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncHttpGet extends AsyncTask<URL, Integer, String> {

    @Override
    protected String doInBackground(URL... urls) {
        return new HttpGet().execute(urls[0]);
    }
}

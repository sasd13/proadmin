package com.sasd13.proadmin.wsclient;

import android.os.AsyncTask;

import com.sasd13.javaex.wsclient.HttpPost;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncHttpPost extends AsyncTask<URL, Integer, Long> {

    private String contentType;
    private String sData;

    public AsyncHttpPost(String contentType, String sData) {
        this.contentType = contentType;
        this.sData = sData;
    }

    @Override
    protected Long doInBackground(URL... urls) {
        return new HttpPost(this.contentType, this.sData).execute(urls[0]);
    }
}

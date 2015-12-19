package com.sasd13.proadmin.wsclient;

import android.os.AsyncTask;

/**
 * Created by Samir on 18/12/2015.
 */
public class TaskCanceler implements Runnable {

    private AsyncTask asyncTask;

    public TaskCanceler(AsyncTask asyncTask) {
        this.asyncTask = asyncTask;
    }

    @Override
    public void run() {
        if (this.asyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            this.asyncTask.cancel(true);
        }
    }
}

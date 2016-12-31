package com.sasd13.proadmin.service;

import com.sasd13.proadmin.ws.LogInPromise;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class LogInService {

    private LogInPromise promise;

    public LogInService(LogInPromise.Callback callback) {
        promise = new LogInPromise(callback);
    }

    public void logIn(String number, String password) {
        promise.logIn(number, password);
    }
}

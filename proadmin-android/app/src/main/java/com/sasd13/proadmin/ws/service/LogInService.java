package com.sasd13.proadmin.ws.service;

import com.sasd13.androidex.ws.rest.service.IWebServiceCaller;
import com.sasd13.javaex.ws.ILoginWebService;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.ws.LogInRESTCallback;

import java.util.List;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class LogInService implements ILoginWebService<Teacher> {

    public interface Caller extends IWebServiceCaller {

        void onLoggedIn(Teacher teacher);
    }

    private Caller caller;
    private LogInRESTCallback callback;

    public LogInService(Caller caller) {
        this.caller = caller;
        callback = new LogInRESTCallback(this);
    }

    public void logIn(String number, String password) {
        callback.logIn(number, password);
    }

    @Override
    public void onPreExecute() {
        caller.onWaiting();
    }

    @Override
    public void onLoggedIn(Teacher teacher) {
        caller.onLoggedIn(teacher);
    }

    @Override
    public void onErrors(List<String> errors) {
        caller.onErrors(errors);
    }
}

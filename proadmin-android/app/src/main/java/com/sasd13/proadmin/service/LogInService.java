package com.sasd13.proadmin.service;

import android.content.Context;

import com.sasd13.javaex.ws.ILoginWebService;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.ws.caller.ILoginWebServiceCaller;
import com.sasd13.proadmin.ws.rest.LogInRESTCallback;

import java.util.List;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class LogInService implements ILoginWebService<Teacher> {

    private Context context;
    private ILoginWebServiceCaller<Teacher> caller;
    private LogInRESTCallback callback;

    public LogInService(Context context, ILoginWebServiceCaller<Teacher> caller) {
        this.context = context;
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
    public void onLogIn(Teacher teacher) {
        caller.onLoggedIn(teacher);
    }

    @Override
    public void onError(List<String> errors) {
        WebServiceUtils.handleErrors(context, caller, errors);
    }
}

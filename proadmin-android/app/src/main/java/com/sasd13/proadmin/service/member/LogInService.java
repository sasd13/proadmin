package com.sasd13.proadmin.service.member;

import android.content.Context;

import com.sasd13.javaex.ws.ILoginWebService;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.ws.caller.ILoginWebServiceCaller;
import com.sasd13.proadmin.ws.rest.LogInRESTCallback;
import com.sasd13.proadmin.ws.wrapper.member.TeacherReadWrapper;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class LogInService implements ILoginWebService<Teacher> {

    private ILoginWebServiceCaller<Teacher> caller;
    private LogInRESTCallback callback;

    public LogInService(Context context, ILoginWebServiceCaller<Teacher> caller) {
        this.caller = caller;
        callback = new LogInRESTCallback(context, this);
    }

    public void logIn(String number, String password) {
        callback.logIn(number, password);
    }

    @Override
    public void onPreExecute() {
        caller.onWait();
    }

    @Override
    public void onLogIn(Teacher teacher) {
        caller.onLogin(new TeacherReadWrapper(teacher));
    }

    @Override
    public void onError(String error) {
        caller.onError(error);
    }
}

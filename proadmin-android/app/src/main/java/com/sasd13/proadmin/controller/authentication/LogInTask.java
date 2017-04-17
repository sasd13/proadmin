package com.sasd13.proadmin.controller.authentication;

import com.sasd13.androidex.util.requestor.ReadRequestorTask;
import com.sasd13.proadmin.service.IAuthenticationService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.aaa.EnumAAASession;

import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class LogInTask extends ReadRequestorTask {

    private LogInController controller;
    private IAuthenticationService authenticationService;

    public LogInTask(LogInController controller, IAuthenticationService authenticationService) {
        this.controller = controller;
        this.authenticationService = authenticationService;
    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();

        controller.onWaiting();
    }

    @Override
    public Object execute(Object in) {
        return authenticationService.logIn((Map<String, String>) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        if (((ServiceResult) out).isSuccess()) {
            Map<String, String> session = (Map<String, String>) ((ServiceResult) out).getResult();

            controller.onReadTeacher(session.get(EnumAAASession.USERID.getName()), session.get(EnumAAASession.INTERMEDIARY.getName()));
        } else {
            controller.onFail(((ServiceResult) out).getHttpStatus());
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.onFail(((ServiceResult) out).getHttpStatus());
    }
}

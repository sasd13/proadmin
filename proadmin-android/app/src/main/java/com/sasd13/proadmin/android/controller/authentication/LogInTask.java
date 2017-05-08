package com.sasd13.proadmin.android.controller.authentication;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.service.IAuthenticationService;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class LogInTask extends RequestorTask {

    private LogInController controller;
    private IAuthenticationService authenticationService;

    public LogInTask(LogInController controller, IAuthenticationService authenticationService) {
        this.controller = controller;
        this.authenticationService = authenticationService;
    }

    @Override
    public Object execute(Object in) {
        return authenticationService.logIn((Map<String, String>) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<Map<String, String>> result = (ServiceResult<Map<String, String>>) out;

        if (result.isSuccess()) {
            controller.onReadSession(result.getData());
        } else {
            controller.onFail(result.getHttpStatus(), result.getErrors());
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.onCancelled();
    }
}

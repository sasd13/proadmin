package com.sasd13.proadmin.android.controller.authentication;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.service.IAuthenticationService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.util.EnumSession;

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
            String userID = result.getData().get(EnumSession.USERID.getKey());
            String intermediary = result.getData().get(EnumSession.INTERMEDIARY.getKey());

            controller.onReadUser(userID, intermediary);
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

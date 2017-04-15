package com.sasd13.proadmin.controller.authentication;

import com.sasd13.androidex.util.requestor.ReadRequestorTask;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.service.IAuthenticationService;
import com.sasd13.proadmin.service.ITeacherService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.aaa.EnumAAASession;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class LogInTask extends ReadRequestorTask {

    private LogInController controller;
    private IAuthenticationService authenticationService;
    private ITeacherService teacherService;

    public LogInTask(LogInController controller, IAuthenticationService authenticationService, ITeacherService teacherService) {
        this.controller = controller;
        this.authenticationService = authenticationService;
        this.teacherService = teacherService;
    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();

        controller.onWaiting();
    }

    @Override
    public Object execute(Object in) {
        ServiceResult result = authenticationService.logIn((Map<String, String>) in);

        if (result.isSuccess()) {
            Map<String, String> session = (Map<String, String>) result.getResult();

            parameters.clear();
            parameters.put(EnumParameter.NUMBER.getName(), new String[]{session.get(EnumAAASession.USERNAME.getName())});

            result = teacherService.read(parameters);
        }

        return result;
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        if (((ServiceResult) out).isSuccess()) {
            controller.onReadTeacher(((ServiceResult<List<Teacher>>) out).getResult().get(0));
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

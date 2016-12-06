package com.sasd13.proadmin.ws.service;

import com.sasd13.androidex.ws.rest.service.ManageService;
import com.sasd13.androidex.ws.rest.service.ReadService;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.Constants;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class RunningService {

    public interface Caller extends ReadService.Caller<Running>, ManageService.Caller {
    }

    private ReadService<Running> readService;
    private ManageService<Running> manageService;

    public RunningService(Caller caller) {
        readService = new ReadService<>(caller, WSResources.URL_WS_RUNNINGS, Running.class);
        manageService = new ManageService<>(caller, WSResources.URL_WS_RUNNINGS);
    }

    public void readByTeacherAndProject(String teacherNumber, String projectCode) {
        readService.clearHeaders();
        readService.clearParameters();
        readService.putHeaders(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        readService.putParameters(EnumParameter.PROJECT.getName(), new String[]{projectCode});
        readService.putParameters(EnumParameter.TEACHER.getName(), new String[]{teacherNumber});
        readService.read();
    }

    public void create(Running running) {
        manageService.create(running);
    }

    public void delete(Running running) {
        manageService.delete(running);
    }
}

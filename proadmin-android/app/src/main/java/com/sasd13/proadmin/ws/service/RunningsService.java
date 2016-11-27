package com.sasd13.proadmin.ws.service;

import com.sasd13.androidex.ws.rest.service.ManageService;
import com.sasd13.androidex.ws.rest.service.ReadService;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class RunningsService {

    public interface ReadCaller extends ReadService.Caller<Running> {
    }

    public interface ManageCaller extends ManageService.Caller {
    }

    private ReadService<Running> readService;
    private ManageService<Running> manageService;

    public RunningsService(ReadCaller caller) {
        readService = new ReadService<>(caller, WSResources.URL_WS_RUNNINGS, Running.class);
    }

    public RunningsService(ManageCaller caller) {
        manageService = new ManageService<>(caller, WSResources.URL_WS_RUNNINGS);
    }

    public void read(Project project, String teacherNumber) {
        readService.clearHeader();
        readService.clearParameters();
        readService.putParameters(EnumParameter.PROJECT.getName(), new String[]{project.getCode()});
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

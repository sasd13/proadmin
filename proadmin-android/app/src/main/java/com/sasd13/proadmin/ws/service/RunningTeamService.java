package com.sasd13.proadmin.ws.service;

import com.sasd13.androidex.ws.rest.service.ManageService;
import com.sasd13.androidex.ws.rest.service.ReadService;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.Constants;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class RunningTeamService {

    public interface Caller extends ReadService.Caller<RunningTeam>, ManageService.Caller {
    }

    private ReadService<RunningTeam> readService;
    private ManageService<RunningTeam> manageService;

    public RunningTeamService(Caller caller) {
        readService = new ReadService<>(caller, WSResources.URL_WS_RUNNINGTEAMS, RunningTeam.class);
        manageService = new ManageService<>(caller, WSResources.URL_WS_RUNNINGTEAMS);
    }

    public void read(String teacherNumber) {
        readService.clearHeaders();
        readService.clearParameters();
        readService.putHeaders(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        readService.putParameters(EnumParameter.TEACHER.getName(), new String[]{teacherNumber});
        readService.read();
    }

    public void create(RunningTeam runningTeam) {
        manageService.create(runningTeam);
    }

    public void update(RunningTeam runningTeam, RunningTeam runningTeamToUpdate) {
        manageService.update(getUpdateWrapper(runningTeam, runningTeamToUpdate));
    }

    private RunningTeamUpdateWrapper getUpdateWrapper(RunningTeam runningTeam, RunningTeam runningTeamToUpdate) {
        RunningTeamUpdateWrapper updateWrapper = new RunningTeamUpdateWrapper();

        updateWrapper.setWrapped(runningTeam);
        updateWrapper.setRunningYear(runningTeamToUpdate.getRunning().getYear());
        updateWrapper.setProjectCode(runningTeamToUpdate.getRunning().getProject().getCode());
        updateWrapper.setTeamNumber(runningTeamToUpdate.getRunning().getTeacher().getNumber());
        updateWrapper.setTeamNumber(runningTeamToUpdate.getTeam().getNumber());
        updateWrapper.setAcademicLevelCode(runningTeamToUpdate.getAcademicLevel().getCode());

        return updateWrapper;
    }

    public void delete(RunningTeam runningTeam) {
        manageService.delete(runningTeam);
    }
}

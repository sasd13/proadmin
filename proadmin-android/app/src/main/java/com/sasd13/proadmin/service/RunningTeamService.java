package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.rest.promise.ManagePromise;
import com.sasd13.androidex.ws.rest.promise.ReadPromise;
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

    public interface Callback extends ReadPromise.Callback<RunningTeam>, ManagePromise.Callback {
    }

    private ReadPromise<RunningTeam> readPromise;
    private ManagePromise<RunningTeam> managePromise;

    public RunningTeamService(Callback callback) {
        readPromise = new ReadPromise<>(callback, WSResources.URL_WS_RUNNINGTEAMS, RunningTeam.class);
        managePromise = new ManagePromise<>(callback, WSResources.URL_WS_RUNNINGTEAMS);
    }

    public void readByTeacher(String teacherNumber) {
        readPromise.clearHeaders();
        readPromise.clearParameters();
        readPromise.putHeaders(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        readPromise.putParameters(EnumParameter.TEACHER.getName(), new String[]{teacherNumber});
        readPromise.read();
    }

    public void create(RunningTeam runningTeam) {
        managePromise.create(runningTeam);
    }

    public void update(RunningTeam runningTeam, RunningTeam runningTeamToUpdate) {
        managePromise.update(getUpdateWrapper(runningTeam, runningTeamToUpdate));
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
        managePromise.delete(runningTeam);
    }
}

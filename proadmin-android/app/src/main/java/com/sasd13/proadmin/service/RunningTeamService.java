package com.sasd13.proadmin.service;

import com.sasd13.androidex.net.ICallback;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class RunningTeamService {

    private Promise promiseRead, promiseCreate, promiseUpdate, promiseDelete;
    private Map<String, String[]> parameters;

    public RunningTeamService() {
        promiseRead = new Promise("GET", WSResources.URL_WS_RUNNINGTEAMS, RunningTeam.class);
        promiseCreate = new Promise("POST", WSResources.URL_WS_RUNNINGTEAMS);
        promiseUpdate = new Promise("PUT", WSResources.URL_WS_RUNNINGTEAMS);
        promiseDelete = new Promise("DELETE", WSResources.URL_WS_RUNNINGTEAMS);
        parameters = new HashMap<>();

        promiseRead.setParameters(parameters);
    }

    public void readByTeacher(ICallback callback, String teacherNumber) {
        parameters.clear();
        parameters.put(EnumParameter.TEACHER.getName(), new String[]{teacherNumber});
        promiseRead.registerCallback(callback);
        promiseRead.execute();
    }

    public void create(ICallback callback, RunningTeam runningTeam) {
        promiseCreate.registerCallback(callback);
        promiseCreate.execute(runningTeam);
    }

    public void update(ICallback callback, RunningTeam runningTeam, RunningTeam runningTeamToUpdate) {
        promiseUpdate.registerCallback(callback);
        promiseUpdate.execute(getUpdateWrapper(runningTeam, runningTeamToUpdate));
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

    public void delete(ICallback callback, RunningTeam runningTeam) {
        promiseDelete.registerCallback(callback);
        promiseDelete.execute(runningTeam);
    }
}

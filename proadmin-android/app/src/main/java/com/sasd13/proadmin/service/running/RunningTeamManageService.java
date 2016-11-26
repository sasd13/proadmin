package com.sasd13.proadmin.service.running;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.rest.CreateTask;
import com.sasd13.androidex.ws.rest.DeleteTask;
import com.sasd13.androidex.ws.rest.UpdateTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.gui.form.RunningTeamForm;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningTeamUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

public class RunningTeamManageService implements IHttpCallback {

    private static final int TASKTYPE_CREATE = 0;
    private static final int TASKTYPE_UPDATE = 1;
    private static final int TASKTYPE_DELETE = 2;

    private IManageServiceCaller<RunningTeam> serviceCaller;
    private CreateTask<RunningTeam> createTask;
    private UpdateTask<RunningTeam> updateTask;
    private DeleteTask<RunningTeam> deleteTask;
    private RunningTeam runningTeam;
    private int taskType;

    public RunningTeamManageService(IManageServiceCaller<RunningTeam> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void create(RunningTeamForm runningTeamForm) {
        taskType = TASKTYPE_CREATE;
        createTask = new CreateTask<>(WSResources.URL_WS_RUNNINGTEAMS, this);

        try {
            runningTeam = getRunningTeamToCreate(runningTeamForm);

            createTask.execute(runningTeam);
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private RunningTeam getRunningTeamToCreate(RunningTeamForm runningTeamForm) throws FormException {
        RunningTeam runningTeamToCreate = new RunningTeam(
                runningTeamForm.getYear(),
                runningTeamForm.getRunning().getProject().getCode(),
                runningTeamForm.getRunning().getTeacher().getNumber(),
                runningTeamForm.getTeam().getNumber(),
                runningTeamForm.getAcademicLevel().getCode());

        return runningTeamToCreate;
    }

    public void update(RunningTeamForm runningTeamForm, RunningTeam runningTeam) {
        taskType = TASKTYPE_UPDATE;
        updateTask = new UpdateTask<>(WSResources.URL_WS_RUNNINGTEAMS, this);

        try {
            updateTask.execute(getRunningTeamUpdateWrapper(runningTeamForm, runningTeam));
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private IRunningTeamUpdateWrapper getRunningTeamUpdateWrapper(RunningTeamForm runningTeamForm, RunningTeam runningTeam) throws FormException {
        IRunningTeamUpdateWrapper runningTeamUpdateWrapper = new RunningTeamUpdateWrapper();

        runningTeamUpdateWrapper.setWrapped(getRunningTeamToUpdate(runningTeamForm, runningTeam.getRunning()));
        runningTeamUpdateWrapper.setRunningYear(runningTeam.getRunning().getYear());
        runningTeamUpdateWrapper.setProjectCode(runningTeam.getRunning().getProject().getCode());
        runningTeamUpdateWrapper.setTeacherNumber(runningTeam.getRunning().getTeacher().getNumber());
        runningTeamUpdateWrapper.setTeamNumber(runningTeam.getTeam().getNumber());
        runningTeamUpdateWrapper.setAcademicLevelCode(runningTeam.getAcademicLevel().getCode());

        return runningTeamUpdateWrapper;
    }

    private RunningTeam getRunningTeamToUpdate(RunningTeamForm runningTeamForm, Running running) throws FormException {
        RunningTeam runningTeamToUpdate = new RunningTeam(
                running.getYear(),
                running.getProject().getCode(),
                running.getTeacher().getNumber(),
                runningTeamForm.getTeam().getNumber(),
                runningTeamForm.getAcademicLevel().getCode());

        return runningTeamToUpdate;
    }

    public void delete(RunningTeam runningTeam) {
        taskType = TASKTYPE_DELETE;
        deleteTask = new DeleteTask<>(WSResources.URL_WS_RUNNINGTEAMS, this);

        deleteTask.execute(runningTeam);
    }

    @Override
    public void onLoad() {
        serviceCaller.onLoad();
    }

    @Override
    public void onSuccess() {
        switch (taskType) {
            case TASKTYPE_CREATE:
                onCreateTaskSucceeded();
                break;
            case TASKTYPE_UPDATE:
                onUpdateTaskSucceeded();
                break;
            case TASKTYPE_DELETE:
                onDeleteTaskSucceeded();
                break;
        }
    }

    private void onCreateTaskSucceeded() {
        if (!createTask.getResponseErrors().isEmpty()) {
            WebServiceUtils.handleErrors(serviceCaller, createTask.getResponseErrors());
        } else {
            serviceCaller.onCreateSucceeded(runningTeam);
        }
    }

    private void onUpdateTaskSucceeded() {
        if (!updateTask.getResponseErrors().isEmpty()) {
            WebServiceUtils.handleErrors(serviceCaller, updateTask.getResponseErrors());
        } else {
            serviceCaller.onUpdateSucceeded();
        }
    }

    private void onDeleteTaskSucceeded() {
        if (!deleteTask.getResponseErrors().isEmpty()) {
            WebServiceUtils.handleErrors(serviceCaller, deleteTask.getResponseErrors());
        } else {
            serviceCaller.onDeleteSucceeded();
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}
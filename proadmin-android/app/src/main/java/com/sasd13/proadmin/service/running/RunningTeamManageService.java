package com.sasd13.proadmin.service.running;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.rest.CreateTask;
import com.sasd13.androidex.ws.rest.DeleteTask;
import com.sasd13.androidex.ws.rest.UpdateTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.gui.form.RunningTeamForm;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.builder.running.RunningTeamBaseBuilder;
import com.sasd13.proadmin.util.ws.WSResources;

public class RunningTeamManageService implements IHttpCallback {

    private static final int TASKTYPE_CREATE = 0;
    private static final int TASKTYPE_UPDATE = 1;
    private static final int TASKTYPE_DELETE = 2;

    private IManageServiceCaller<RunningTeam> serviceCaller;
    private CreateTask<RunningTeam> createTask;
    private UpdateTask<RunningTeam> updateTask;
    private DeleteTask<RunningTeam> deleteTask;
    private RunningTeam running;
    private int taskType;

    public RunningTeamManageService(IManageServiceCaller<RunningTeam> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void createRunningTeam(RunningTeamForm runningTeamForm, String teacherNumber) {
        taskType = TASKTYPE_CREATE;

        try {
            running = getRunningTeamToCreate(runningTeamForm, teacherNumber);
            createTask = new CreateTask<>(WSResources.URL_WS_RUNNINGS, this);

            createTask.execute(running);
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private RunningTeam getRunningTeamToCreate(RunningTeamForm runningTeamForm, String teacherNumber) throws FormException {
        RunningTeam runningTeamToCreate = new RunningTeamBaseBuilder(
                runningTeamForm.getYear(),
                runningTeamForm.getProject().getCode(),
                teacherNumber,
                runningTeamForm.getNumber(),
                runningTeamForm.getAcademicLevel().getCode()).build();

        return runningTeamToCreate;
    }

    public void updateRunningTeam(RunningTeamForm runningTeamForm, String projectCode, String teacherNumber) {
        taskType = TASKTYPE_UPDATE;

        try {
            running = getRunningTeamToUpdate(runningTeamForm, projectCode, teacherNumber);
            updateTask = new UpdateTask<>(WSResources.URL_WS_RUNNINGS, this);

            updateTask.execute(running);
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private RunningTeam getRunningTeamToUpdate(RunningTeamForm runningTeamForm, String projectCode, String teacherNumber) throws FormException {
        RunningTeam runningToUpdate = new RunningTeamBaseBuilder(
                runningTeamForm.getYear(),
                runningTeamForm.getProject().getCode(),
                teacherNumber,
                runningTeamForm.getNumber(),
                runningTeamForm.getAcademicLevel().getCode()).build();

        return runningToUpdate;
    }

    public void deleteRunningTeam(RunningTeamForm runningTeamForm, String teacherNumber) {
        try {
            taskType = TASKTYPE_DELETE;
            running = getRunningTeamToDelete(runningTeamForm, teacherNumber);
            deleteTask = new DeleteTask<>(WSResources.URL_WS_RUNNINGS, this);

            deleteTask.execute(running);
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private RunningTeam getRunningTeamToDelete(RunningTeamForm runningTeamForm, String teacherNumber) throws FormException {
        RunningTeam runningToDelete = new RunningTeamBaseBuilder(
                runningTeamForm.getYear(),
                runningTeamForm.getProject().getCode(),
                teacherNumber,
                runningTeamForm.getNumber(),
                runningTeamForm.getAcademicLevel().getCode()).build();

        return runningToDelete;
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
            ServiceCallerUtils.handleErrors(serviceCaller, createTask.getResponseErrors());
        } else {
            try {
                serviceCaller.onCreateSucceeded(running);
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(R.string.error_ws_retrieve_data);
            }
        }
    }

    private void onUpdateTaskSucceeded() {
        if (!updateTask.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, updateTask.getResponseErrors());
        } else {
            serviceCaller.onUpdateSucceeded(running);
        }
    }

    private void onDeleteTaskSucceeded() {
        if (!deleteTask.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, deleteTask.getResponseErrors());
        } else {
            serviceCaller.onDeleteSucceeded();
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}
package com.sasd13.proadmin.service.running;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.rest.CreateTask;
import com.sasd13.androidex.ws.rest.DeleteTask;
import com.sasd13.androidex.ws.rest.UpdateTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.gui.form.RunningForm;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.builder.running.RunningBaseBuilder;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

public class RunningManageService implements IHttpCallback {

    private static final int TASKTYPE_CREATE = 0;
    private static final int TASKTYPE_UPDATE = 1;
    private static final int TASKTYPE_DELETE = 2;

    private IManageServiceCaller<Running> serviceCaller;
    private CreateTask<Running> createTask;
    private UpdateTask<Running> updateTask;
    private DeleteTask<Running> deleteTask;
    private Running running;
    private int taskType;

    public RunningManageService(IManageServiceCaller<Running> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void createRunning(RunningForm runningForm, String teacherNumber) {
        taskType = TASKTYPE_CREATE;

        try {
            running = getRunningToCreate(runningForm, teacherNumber);
            createTask = new CreateTask<>(WSResources.URL_WS_RUNNINGS, this);

            createTask.execute(running);
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private Running getRunningToCreate(RunningForm runningForm, String teacherNumber) throws FormException {
        Running runningToCreate = new RunningBaseBuilder(runningForm.getYear(), runningForm.getProject().getCode(), teacherNumber).build();

        return runningToCreate;
    }

    public void updateRunning(RunningForm runningForm, Running running) {
        taskType = TASKTYPE_UPDATE;

        try {
            updateTask = new UpdateTask<>(WSResources.URL_WS_RUNNINGS, this);

            updateTask.execute(getRunningUpdateWrapper(runningForm, running));
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private IRunningUpdateWrapper getRunningUpdateWrapper(RunningForm runningForm, Running running) throws FormException {
        IRunningUpdateWrapper runningUpdateWrapper = new RunningUpdateWrapper();

        runningUpdateWrapper.setWrapped(getRunningToUpdate(runningForm, running.getTeacher()));
        runningUpdateWrapper.setYear(running.getYear());
        runningUpdateWrapper.setProjectCode(running.getProject().getCode());
        runningUpdateWrapper.setTeacherNumber(running.getTeacher().getNumber());

        return runningUpdateWrapper;
    }

    private Running getRunningToUpdate(RunningForm runningForm, Teacher teacher) throws FormException {
        Running runningToUpdate = new RunningBaseBuilder(
                runningForm.getYear(),
                runningForm.getProject().getCode(),
                teacher.getNumber()).build();

        return runningToUpdate;
    }

    public void deleteRunning(Running running) {
        taskType = TASKTYPE_DELETE;
        deleteTask = new DeleteTask<>(WSResources.URL_WS_RUNNINGS, this);

        deleteTask.execute(running);
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
            serviceCaller.onUpdateSucceeded();
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
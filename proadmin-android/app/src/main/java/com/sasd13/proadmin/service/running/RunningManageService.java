package com.sasd13.proadmin.service.running;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.rest.CreateTask;
import com.sasd13.androidex.ws.rest.DeleteTask;
import com.sasd13.androidex.ws.rest.UpdateTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.gui.form.RunningForm;
import com.sasd13.proadmin.util.WebServiceUtils;
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

    public void create(RunningForm runningForm, Project project, String teacherNumber) {
        taskType = TASKTYPE_CREATE;
        createTask = new CreateTask<>(WSResources.URL_WS_RUNNINGS, this);

        try {
            running = getRunningToCreate(runningForm, project, teacherNumber);

            createTask.execute(running);
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private Running getRunningToCreate(RunningForm runningForm, Project project, String teacherNumber) throws FormException {
        Running runningToCreate = new Running(runningForm.getYear(), project.getCode(), teacherNumber);

        return runningToCreate;
    }

    public void delete(Running running) {
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
            WebServiceUtils.handleErrors(serviceCaller, createTask.getResponseErrors());
        } else {
            serviceCaller.onCreateSucceeded(running);
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
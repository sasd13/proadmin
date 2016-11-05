package com.sasd13.proadmin.service.running;

import com.sasd13.androidex.ws.rest.CreateTask;
import com.sasd13.androidex.ws.rest.DeleteTask;
import com.sasd13.androidex.ws.rest.UpdateTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.form.FormException;
import com.sasd13.proadmin.form.RunningForm;
import com.sasd13.proadmin.service.IManageServiceCaller;
import com.sasd13.proadmin.util.Binder;
import com.sasd13.proadmin.util.EnumErrorRes;
import com.sasd13.proadmin.util.exception.EnumError;
import com.sasd13.proadmin.util.ws.WSConstants;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;

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

    public void createRunning(RunningForm runningForm, Project project, Teacher teacher) {
        taskType = TASKTYPE_CREATE;

        try {
            running = getRunningToCreate(runningForm, project, teacher);
            createTask = new CreateTask<>(WSResources.URL_WS_RUNNINGS, this);

            createTask.setTimeout(WSConstants.DEFAULT_TIMEOUT);
            createTask.execute(running);
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private Running getRunningToCreate(RunningForm runningForm, Project project, Teacher teacher) throws FormException {
        Running runningFromForm = runningForm.getEditable();
        Running runningToCreate = new Running();

        Binder.bind(runningToCreate, runningFromForm);

        runningToCreate.setProject(project);
        runningToCreate.setTeacher(teacher);

        return runningToCreate;
    }

    public void updateRunning(Running runningToUpdate, RunningForm runningForm) {
        taskType = TASKTYPE_UPDATE;

        try {
            running = new Running();

            Binder.bind(running, runningToUpdate);
            Binder.bind(running, runningForm.getEditable());

            updateTask = new UpdateTask<>(WSResources.URL_WS_RUNNINGS, this);

            updateTask.setTimeout(WSConstants.DEFAULT_TIMEOUT);
            updateTask.execute(running);
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
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

    private void handleErrors(List<String> errors) {
        EnumError error = EnumError.find(Integer.parseInt(errors.get(0)));

        serviceCaller.onError(EnumErrorRes.find(error).getStringRes());
    }

    private void onCreateTaskSucceeded() {
        if (!createTask.getResponseErrors().isEmpty()) {
            handleErrors(createTask.getResponseErrors());
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
            handleErrors(updateTask.getResponseErrors());
        } else {
            serviceCaller.onUpdateSucceeded(running);
        }
    }

    private void onDeleteTaskSucceeded() {
        if (!deleteTask.getResponseErrors().isEmpty()) {
            handleErrors(createTask.getResponseErrors());
        } else {
            serviceCaller.onDeleteSucceeded();
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}
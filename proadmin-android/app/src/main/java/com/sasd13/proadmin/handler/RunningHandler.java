package com.sasd13.proadmin.handler;

import com.sasd13.androidex.net.ws.IWSPromise;
import com.sasd13.androidex.net.ws.rest.task.CreateTask;
import com.sasd13.androidex.net.ws.rest.task.DeleteTask;
import com.sasd13.androidex.net.ws.rest.task.UpdateTask;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.builder.running.DefaultRunningBuilder;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.form.FormException;
import com.sasd13.proadmin.form.RunningForm;
import com.sasd13.proadmin.activities.fragments.running.RunningFragment;
import com.sasd13.proadmin.util.Binder;
import com.sasd13.proadmin.util.EnumWSCodeRes;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.ws.EnumWSCode;
import com.sasd13.proadmin.ws.WSInformation;

import java.util.List;

public class RunningHandler implements IWSPromise {

    private static final int TASKTYPE_CREATE = 0;
    private static final int TASKTYPE_UPDATE = 1;
    private static final int TASKTYPE_DELETE = 2;

    private RunningFragment runningFragment;
    private DefaultRunningBuilder defaultRunningBuilder;
    private CreateTask<Running> createTask;
    private UpdateTask<Running> updateTask;
    private DeleteTask<Running> deleteTask;
    private Running running;
    private int taskType;

    public RunningHandler(RunningFragment runningFragment) {
        this.runningFragment = runningFragment;
        defaultRunningBuilder = new DefaultRunningBuilder();
    }

    public List<Project> readProjectsFromCache() {
        return Cache.loadAll(runningFragment.getContext(), Project.class);
    }

    public Running getDefaultValueOfRunning() {
        return defaultRunningBuilder.build();
    }

    public void createRunning(RunningForm runningForm) {
        taskType = TASKTYPE_CREATE;

        try {
            running = getRunningToCreate(runningForm);
            createTask = new CreateTask<>(Running.class, WSInformation.URL_WS_RUNNINGS, this);

            createTask.execute(running);
        } catch (FormException e) {
            runningFragment.onError(e.getResMessage());
        }
    }

    private Running getRunningToCreate(RunningForm runningForm) throws FormException {
        Running runningFromForm = runningForm.getEditable();
        Running runningToCreate = new Running();

        Binder.bind(runningToCreate, runningFromForm);

        runningToCreate.setTeacher(Cache.load(
                runningFragment.getContext(),
                SessionHelper.getExtraId(runningFragment.getContext(), Extra.TEACHER_ID),
                Teacher.class));

        return runningToCreate;
    }

    public void updateRunning(Running runningToUpdate, RunningForm runningForm) {
        taskType = TASKTYPE_UPDATE;

        try {
            Running runningFromForm = runningForm.getEditable();

            Binder.bind(runningToUpdate, runningFromForm);

            running = runningToUpdate;
            running.setTeacher(Cache.load(
                    runningFragment.getContext(),
                    SessionHelper.getExtraId(runningFragment.getContext(), Extra.TEACHER_ID),
                    Teacher.class));

            updateTask = new UpdateTask<>(Running.class, WSInformation.URL_WS_RUNNINGS, this);
            updateTask.execute(running);
        } catch (FormException e) {
            runningFragment.onError(e.getResMessage());
        }
    }

    public void deleteRunning(Running running) {
        taskType = TASKTYPE_DELETE;
        this.running = running;
        deleteTask = new DeleteTask<>(Running.class, WSInformation.URL_WS_RUNNINGS, this);

        deleteTask.execute(running);
    }

    @Override
    public void onLoad() {
        runningFragment.onLoad();
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
        EnumWSCode wsCode = EnumWSCode.find(createTask.getWSResponseCode());

        if (wsCode.isError()) {
            showError(wsCode);
        } else {
            try {
                running.setId(createTask.getResults().get(0));
                Cache.keep(runningFragment.getContext(), running);
                runningFragment.onCreateSucceeded();
            } catch (IndexOutOfBoundsException e) {
                runningFragment.onError(R.string.ws_error_data_retrieval_error);
            }
        }
    }

    private void showError(EnumWSCode wsCode) {
        EnumWSCodeRes wsCodeRes = EnumWSCodeRes.find(wsCode);
        runningFragment.onError(wsCodeRes.getStringRes());
    }

    private void onUpdateTaskSucceeded() {
        EnumWSCode wsCode = EnumWSCode.find(updateTask.getWSReponseCode());

        if (wsCode.isError()) {
            showError(wsCode);
        } else {
            Cache.keep(runningFragment.getContext(), running);
            runningFragment.onUpdateSucceeded();
        }
    }

    private void onDeleteTaskSucceeded() {
        EnumWSCode wsCode = EnumWSCode.find(deleteTask.getWSReponseCode());

        if (wsCode.isError()) {
            showError(wsCode);
        } else {
            Cache.delete(runningFragment.getContext(), running);
            runningFragment.onDeleteSucceeded();
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        runningFragment.onError(R.string.ws_error_server_connection_failed);
    }
}
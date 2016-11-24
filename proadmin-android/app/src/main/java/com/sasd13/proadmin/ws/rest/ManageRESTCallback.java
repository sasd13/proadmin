package com.sasd13.proadmin.ws.rest;

import android.content.Context;

import com.sasd13.androidex.ws.rest.CreateTask;
import com.sasd13.androidex.ws.rest.DeleteTask;
import com.sasd13.androidex.ws.rest.UpdateTask;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.service.IManageServiceCaller;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.util.ServiceCallerUtils;

public class ManageRESTCallback<T> extends RESTWebService {

    private static final int TASKTYPE_CREATE = 0;
    private static final int TASKTYPE_UPDATE = 1;
    private static final int TASKTYPE_DELETE = 2;

    private CreateTask<T> createTask;
    private UpdateTask<T> updateTask;
    private DeleteTask<T> deleteTask;
    private int taskType;

    public ManageRESTCallback(Context context, String url, IManageServiceCaller<T> serviceCaller) {
        super(context, url, serviceCaller);
    }

    public void create(T t) {
        taskType = TASKTYPE_CREATE;
        createTask = new CreateTask<>(url, this);

        createTask.execute(t);
    }

    public void update(IUpdateWrapper<T> updateWrapper) {
        taskType = TASKTYPE_UPDATE;
        updateTask = new UpdateTask<>(url, this);

        updateTask.execute(updateWrapper);
    }

    public void delete(T t) {
        taskType = TASKTYPE_DELETE;
        deleteTask = new DeleteTask<>(url, this);

        deleteTask.execute(t);
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
            ServiceCallerUtils.handleErrors(context, serviceCaller, createTask.getResponseErrors());
        } else {
            ((IManageServiceCaller<T>) serviceCaller).onCreate();
        }
    }

    private void onUpdateTaskSucceeded() {
        if (!updateTask.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(context, serviceCaller, updateTask.getResponseErrors());
        } else {
            ((IManageServiceCaller<T>) serviceCaller).onUpdate();
        }
    }

    private void onDeleteTaskSucceeded() {
        if (!deleteTask.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(context, serviceCaller, deleteTask.getResponseErrors());
        } else {
            ((IManageServiceCaller<T>) serviceCaller).onDelete();
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        serviceCaller.onError(context.getResources().getString(R.string.error_ws_server_connection));
    }
}
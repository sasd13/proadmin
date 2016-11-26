package com.sasd13.proadmin.ws.rest;

import android.content.Context;

import com.sasd13.androidex.ws.rest.CreateTask;
import com.sasd13.androidex.ws.rest.DeleteTask;
import com.sasd13.androidex.ws.rest.UpdateTask;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.ws.IManageWebService;
import com.sasd13.proadmin.util.WebServiceUtils;

public class ManageRESTCallback<T> extends RESTCallback {

    private static final int TASKTYPE_CREATE = 0;
    private static final int TASKTYPE_UPDATE = 1;
    private static final int TASKTYPE_DELETE = 2;

    private CreateTask<T> createTask;
    private UpdateTask<T> updateTask;
    private DeleteTask<T> deleteTask;
    private int taskType;

    public ManageRESTCallback(Context context, String url, IManageWebService webService) {
        super(context, url, webService);
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
            WebServiceUtils.handleErrors(context, webService, createTask.getResponseErrors());
        } else {
            ((IManageWebService) webService).onCreate();
        }
    }

    private void onUpdateTaskSucceeded() {
        if (!updateTask.getResponseErrors().isEmpty()) {
            WebServiceUtils.handleErrors(context, webService, updateTask.getResponseErrors());
        } else {
            ((IManageWebService) webService).onUpdate();
        }
    }

    private void onDeleteTaskSucceeded() {
        if (!deleteTask.getResponseErrors().isEmpty()) {
            WebServiceUtils.handleErrors(context, webService, deleteTask.getResponseErrors());
        } else {
            ((IManageWebService) webService).onDelete();
        }
    }
}
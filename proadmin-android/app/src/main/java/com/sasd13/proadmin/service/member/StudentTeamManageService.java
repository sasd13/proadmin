package com.sasd13.proadmin.service.member;

import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.rest.CreateTask;
import com.sasd13.androidex.ws.rest.DeleteTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.builder.member.StudentTeamBaseBuilder;
import com.sasd13.proadmin.util.ws.WSResources;

public class StudentTeamManageService implements IHttpCallback {

    private static final int TASKTYPE_CREATE = 0;
    private static final int TASKTYPE_DELETE = 1;

    private IManageServiceCaller<StudentTeam> serviceCaller;
    private CreateTask<StudentTeam> createTask;
    private DeleteTask<StudentTeam> deleteTask;
    private StudentTeam studentTeam;
    private int taskType;

    public StudentTeamManageService(IManageServiceCaller<StudentTeam> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void createStudentTeam(String studentNumber, String teamNumber) {
        taskType = TASKTYPE_CREATE;
        studentTeam = getStudentTeamToCreate(studentNumber, teamNumber);
        createTask = new CreateTask<>(WSResources.URL_WS_STUDENTTEAMS, this);

        createTask.execute(studentTeam);
    }

    private StudentTeam getStudentTeamToCreate(String studentNumber, String teamNumber) {
        StudentTeam studentTeamToCreate = new StudentTeamBaseBuilder(studentNumber, teamNumber).build();

        return studentTeamToCreate;
    }

    public void deleteStudentTeam(StudentTeam studentTeam) {
        taskType = TASKTYPE_DELETE;
        deleteTask = new DeleteTask<>(WSResources.URL_WS_STUDENTTEAMS, this);

        deleteTask.execute(studentTeam);
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
                serviceCaller.onCreateSucceeded(studentTeam);
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(R.string.error_ws_retrieve_data);
            }
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
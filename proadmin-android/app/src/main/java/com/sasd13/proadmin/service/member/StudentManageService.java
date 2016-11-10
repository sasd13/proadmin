package com.sasd13.proadmin.service.member;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.rest.CreateTask;
import com.sasd13.androidex.ws.rest.DeleteTask;
import com.sasd13.androidex.ws.rest.UpdateTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.gui.form.StudentForm;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.builder.running.StudentBaseBuilder;
import com.sasd13.proadmin.util.ws.WSResources;

public class StudentManageService implements IHttpCallback {

    private static final int TASKTYPE_CREATE = 0;
    private static final int TASKTYPE_UPDATE = 1;
    private static final int TASKTYPE_DELETE = 2;

    private IManageServiceCaller<Student> serviceCaller;
    private CreateTask<Student> createTask;
    private UpdateTask<Student> updateTask;
    private DeleteTask<Student> deleteTask;
    private Student running;
    private int taskType;

    public StudentManageService(IManageServiceCaller<Student> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void createStudent(StudentForm runningForm, String teacherNumber) {
        taskType = TASKTYPE_CREATE;

        try {
            running = getStudentToCreate(runningForm, teacherNumber);
            createTask = new CreateTask<>(WSResources.URL_WS_RUNNINGS, this);

            createTask.execute(running);
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private Student getStudentToCreate(StudentForm runningForm, String teacherNumber) throws FormException {
        Student runningToCreate = new StudentBaseBuilder(runningForm.getYear(), runningForm.getProject().getCode(), teacherNumber).build();

        return runningToCreate;
    }

    public void updateStudent(StudentForm runningForm, String projectCode, String teacherNumber) {
        taskType = TASKTYPE_UPDATE;

        try {
            running = getStudentToUpdate(runningForm, projectCode, teacherNumber);
            updateTask = new UpdateTask<>(WSResources.URL_WS_RUNNINGS, this);

            updateTask.execute(running);
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private Student getStudentToUpdate(StudentForm runningForm, String projectCode, String teacherNumber) throws FormException {
        Student runningToUpdate = new StudentBaseBuilder(runningForm.getYear(), projectCode, teacherNumber).build();

        return runningToUpdate;
    }

    public void deleteStudent(StudentForm runningForm, String teacherNumber) {
        try {
            taskType = TASKTYPE_DELETE;
            running = getStudentToDelete(runningForm, teacherNumber);
            deleteTask = new DeleteTask<>(WSResources.URL_WS_RUNNINGS, this);

            deleteTask.execute(running);
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private Student getStudentToDelete(StudentForm runningForm, String teacherNumber) throws FormException {
        Student runningToDelete = new StudentBaseBuilder(runningForm.getYear(), runningForm.getProject().getCode(), teacherNumber).build();

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
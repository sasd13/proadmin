package com.sasd13.proadmin.service.member;

import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.rest.CreateTask;
import com.sasd13.androidex.ws.rest.UpdateTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.util.wrapper.update.member.IStudentUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

public class StudentManageService implements IHttpCallback {

    private static final int TASKTYPE_CREATE = 0;
    private static final int TASKTYPE_UPDATE = 1;

    private IManageServiceCaller<Student> serviceCaller;
    private CreateTask<Student> createTask;
    private UpdateTask<Student> updateTask;
    private int taskType;
    private Student student;

    public StudentManageService(IManageServiceCaller<Student> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void create(Student studentFromForm) {
        taskType = TASKTYPE_CREATE;
        student = studentFromForm;
        createTask = new CreateTask<>(WSResources.URL_WS_STUDENTS, this);

        createTask.execute(student);
    }

    public void update(Student studentFromForm, Student student) {
        taskType = TASKTYPE_UPDATE;
        updateTask = new UpdateTask<>(WSResources.URL_WS_STUDENTS, this);

        updateTask.execute(getStudentUpdateWrapper(studentFromForm, student));
    }

    private IStudentUpdateWrapper getStudentUpdateWrapper(Student studentForm, Student student) {
        IStudentUpdateWrapper studentUpdateWrapper = new StudentUpdateWrapper();

        studentUpdateWrapper.setWrapped(studentForm);
        studentUpdateWrapper.setNumber(student.getNumber());

        return studentUpdateWrapper;
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
        }
    }

    private void onCreateTaskSucceeded() {
        if (!createTask.getResponseErrors().isEmpty()) {
            WebServiceUtils.handleErrors(serviceCaller, createTask.getResponseErrors());
        } else {
            serviceCaller.onCreateSucceeded(student);
        }
    }

    private void onUpdateTaskSucceeded() {
        if (!updateTask.getResponseErrors().isEmpty()) {
            WebServiceUtils.handleErrors(serviceCaller, updateTask.getResponseErrors());
        } else {
            serviceCaller.onUpdateSucceeded();
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}
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
import com.sasd13.proadmin.util.builder.member.StudentBaseBuilder;
import com.sasd13.proadmin.util.wrapper.update.member.IStudentUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

public class StudentManageService implements IHttpCallback {

    private static final int TASKTYPE_CREATE = 0;
    private static final int TASKTYPE_UPDATE = 1;
    private static final int TASKTYPE_DELETE = 2;

    private IManageServiceCaller<Student> serviceCaller;
    private CreateTask<Student> createTask;
    private UpdateTask<Student> updateTask;
    private DeleteTask<Student> deleteTask;
    private Student student;
    private int taskType;

    public StudentManageService(IManageServiceCaller<Student> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void createStudent(StudentForm studentForm, String teamNumber) {
        taskType = TASKTYPE_CREATE;

        try {
            student = getStudentToCreate(studentForm);
            createTask = new CreateTask<>(WSResources.URL_WS_STUDENTS, this);

            createTask.execute(student);
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private Student getStudentToCreate(StudentForm studentForm) throws FormException {
        Student studentToCreate = new StudentBaseBuilder(studentForm.getNumber()).build();

        studentToCreate.setFirstName(studentForm.getFirstName());
        studentToCreate.setLastName(studentForm.getLastName());
        studentToCreate.setEmail(studentForm.getEmail());

        return studentToCreate;
    }

    public void updateStudent(StudentForm studentForm, Student student) {
        taskType = TASKTYPE_UPDATE;

        try {
            updateTask = new UpdateTask<>(WSResources.URL_WS_STUDENTS, this);

            updateTask.execute(getStudentUpdateWrapper(studentForm, student));
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private IStudentUpdateWrapper getStudentUpdateWrapper(StudentForm studentForm, Student student) throws FormException {
        IStudentUpdateWrapper studentUpdateWrapper = new StudentUpdateWrapper();

        studentUpdateWrapper.setWrapped(getStudentToUpdate(studentForm));
        studentUpdateWrapper.setNumber(student.getNumber());

        return studentUpdateWrapper;
    }

    private Student getStudentToUpdate(StudentForm studentForm) throws FormException {
        Student studentToUpdate = new StudentBaseBuilder(studentForm.getNumber()).build();

        studentToUpdate.setFirstName(studentForm.getFirstName());
        studentToUpdate.setLastName(studentForm.getLastName());
        studentToUpdate.setEmail(studentForm.getEmail());

        return studentToUpdate;
    }

    public void deleteStudent(Student student) {
        taskType = TASKTYPE_DELETE;
        deleteTask = new DeleteTask<>(WSResources.URL_WS_STUDENTS, this);

        deleteTask.execute(student);
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
                serviceCaller.onCreateSucceeded(student);
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
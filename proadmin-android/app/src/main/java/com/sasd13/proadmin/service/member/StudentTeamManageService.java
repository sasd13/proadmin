package com.sasd13.proadmin.service.member;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.rest.CreateTask;
import com.sasd13.androidex.ws.rest.DeleteTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.gui.form.StudentForm;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.builder.member.StudentBaseBuilder;
import com.sasd13.proadmin.util.builder.member.StudentTeamBaseBuilder;
import com.sasd13.proadmin.util.ws.WSResources;

public class StudentTeamManageService implements IHttpCallback {

    private static final int TASKTYPE_CREATE_STUDENT = 0;
    private static final int TASKTYPE_CREATE_STUDENTTEAM = 1;
    private static final int TASKTYPE_DELETE_STUDENTTEAM = 2;

    private IManageServiceCaller<StudentTeam> serviceCaller;
    private CreateTask<Student> createTaskStudent;
    private CreateTask<StudentTeam> createTaskStudentTeam;
    private DeleteTask<StudentTeam> deleteTask;
    private StudentTeam studentTeam;
    private int taskType;

    public StudentTeamManageService(IManageServiceCaller<StudentTeam> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void createStudent(StudentForm studentForm, Team team) {
        taskType = TASKTYPE_CREATE_STUDENT;

        try {
            Student student = getStudentToCreate(studentForm);
            studentTeam = getStudentTeamToCreate(student, team);
            createTaskStudent = new CreateTask<>(WSResources.URL_WS_STUDENTS, this);

            createTaskStudent.execute(student);
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

    private StudentTeam getStudentTeamToCreate(Student student, Team team) {
        StudentTeam studentTeamToCreate = new StudentTeamBaseBuilder(student.getNumber(), team.getNumber()).build();

        return studentTeamToCreate;
    }

    public void deleteStudentTeam(StudentTeam studentTeam) {
        taskType = TASKTYPE_DELETE_STUDENTTEAM;
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
            case TASKTYPE_CREATE_STUDENT:
                onCreateStudentTaskSucceeded();
                break;
            case TASKTYPE_CREATE_STUDENTTEAM:
                onCreateStudentTeamTaskSucceeded();
                break;
            case TASKTYPE_DELETE_STUDENTTEAM:
                onDeleteStudentTeamTaskSucceeded();
                break;
        }
    }

    private void onCreateStudentTaskSucceeded() {
        if (!createTaskStudent.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, createTaskStudent.getResponseErrors());
        } else {
            taskType = TASKTYPE_CREATE_STUDENTTEAM;
            createTaskStudentTeam = new CreateTask<>(WSResources.URL_WS_STUDENTTEAMS, this);

            createTaskStudentTeam.execute(studentTeam);
        }
    }

    private void onCreateStudentTeamTaskSucceeded() {
        if (!createTaskStudentTeam.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, createTaskStudentTeam.getResponseErrors());
        } else {
            try {
                serviceCaller.onCreateSucceeded(studentTeam);
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(R.string.error_ws_retrieve_data);
            }
        }
    }

    private void onDeleteStudentTeamTaskSucceeded() {
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
package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.rest.promise.ManagePromise;
import com.sasd13.androidex.ws.rest.promise.ReadPromise;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.Constants;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class StudentService implements ManagePromise.Callback {

    public interface Callback extends ReadPromise.Callback<StudentTeam>, ManagePromise.Callback {
    }

    private static final int TASKTYPE_STUDENT = 0;
    private static final int TASKTYPE_STUDENTTEAM = 1;

    private Callback callback;
    private ReadPromise<StudentTeam> readPromiseStudentTeams;
    private ManagePromise<Student> managePromiseStudents;
    private ManagePromise<StudentTeam> managePromiseStudentTeams;
    private StudentTeam studentTeam;
    private int taskType;

    public StudentService(Callback callback) {
        this.callback = callback;
        readPromiseStudentTeams = new ReadPromise<>(callback, WSResources.URL_WS_STUDENTTEAMS, StudentTeam.class);
        managePromiseStudents = new ManagePromise<>(this, WSResources.URL_WS_STUDENTS);
        managePromiseStudentTeams = new ManagePromise<>(callback, WSResources.URL_WS_STUDENTTEAMS);
    }

    public void readByTeam(String teamNumber) {
        readPromiseStudentTeams.clearHeaders();
        readPromiseStudentTeams.clearParameters();
        readPromiseStudentTeams.putHeaders(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        readPromiseStudentTeams.putParameters(EnumParameter.TEAM.getName(), new String[]{teamNumber});
        readPromiseStudentTeams.read();
    }

    public void create(Student student, Team team) {
        taskType = TASKTYPE_STUDENT;
        studentTeam = new StudentTeam(student.getNumber(), team.getNumber());

        managePromiseStudents.create(student);
    }

    public void update(Student student, Student studentToUpdate) {
        managePromiseStudents.update(getUpdateWrapper(student, studentToUpdate));
    }

    private StudentUpdateWrapper getUpdateWrapper(Student student, Student studentToUpdate) {
        StudentUpdateWrapper updateWrapper = new StudentUpdateWrapper();

        updateWrapper.setWrapped(student);
        updateWrapper.setNumber(studentToUpdate.getNumber());

        return updateWrapper;
    }

    public void delete(StudentTeam studentTeam) {
        managePromiseStudentTeams.delete(studentTeam);
    }

    @Override
    public void onPreExecute() {
        if (taskType == TASKTYPE_STUDENT) {
            callback.onPreExecute();
        }
    }

    @Override
    public void onCreated() {
        if (taskType == TASKTYPE_STUDENT) {
            taskType = TASKTYPE_STUDENTTEAM;

            managePromiseStudentTeams.create(studentTeam);
        }
    }

    @Override
    public void onUpdated() {
        callback.onUpdated();
    }

    @Override
    public void onDeleted() {
        callback.onUpdated();
    }

    @Override
    public void onErrors(List<String> errors) {
        callback.onErrors(errors);
    }
}

package com.sasd13.proadmin.service.ws;

import com.sasd13.androidex.ws.rest.service.ManageService;
import com.sasd13.androidex.ws.rest.service.ReadService;
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

public class StudentService implements ManageService.Caller {

    public interface Caller extends ReadService.Caller<StudentTeam>, ManageService.Caller {
    }

    private static final int TASKTYPE_STUDENT = 0;
    private static final int TASKTYPE_STUDENTTEAM = 1;

    private Caller caller;
    private ReadService<StudentTeam> readServiceStudentTeams;
    private ManageService<Student> manageServiceStudents;
    private ManageService<StudentTeam> manageServiceStudentTeams;
    private StudentTeam studentTeam;
    private int taskType;

    public StudentService(Caller caller) {
        this.caller = caller;
        readServiceStudentTeams = new ReadService<>(caller, WSResources.URL_WS_STUDENTTEAMS, StudentTeam.class);
        manageServiceStudents = new ManageService<>(this, WSResources.URL_WS_STUDENTS);
        manageServiceStudentTeams = new ManageService<>(caller, WSResources.URL_WS_STUDENTTEAMS);
    }

    public void readByTeam(String teamNumber) {
        readServiceStudentTeams.clearHeaders();
        readServiceStudentTeams.clearParameters();
        readServiceStudentTeams.putHeaders(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        readServiceStudentTeams.putParameters(EnumParameter.TEAM.getName(), new String[]{teamNumber});
        readServiceStudentTeams.read();
    }

    public void create(Student student, Team team) {
        taskType = TASKTYPE_STUDENT;
        studentTeam = new StudentTeam(student.getNumber(), team.getNumber());

        manageServiceStudents.create(student);
    }

    public void update(Student student, Student studentToUpdate) {
        manageServiceStudents.update(getUpdateWrapper(student, studentToUpdate));
    }

    private StudentUpdateWrapper getUpdateWrapper(Student student, Student studentToUpdate) {
        StudentUpdateWrapper updateWrapper = new StudentUpdateWrapper();

        updateWrapper.setWrapped(student);
        updateWrapper.setNumber(studentToUpdate.getNumber());

        return updateWrapper;
    }

    public void delete(StudentTeam studentTeam) {
        manageServiceStudentTeams.delete(studentTeam);
    }

    @Override
    public void onWaiting() {
        if (taskType == TASKTYPE_STUDENT) {
            caller.onWaiting();
        }
    }

    @Override
    public void onCreated() {
        if (taskType == TASKTYPE_STUDENT) {
            taskType = TASKTYPE_STUDENTTEAM;

            manageServiceStudentTeams.create(studentTeam);
        }
    }

    @Override
    public void onUpdated() {
        caller.onUpdated();
    }

    @Override
    public void onDeleted() {
        caller.onDeleted();
    }

    @Override
    public void onErrors(List<String> errors) {
        caller.onErrors(errors);
    }
}

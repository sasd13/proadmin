package com.sasd13.proadmin.ws.service;

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

public class StudentsService implements ReadService.Caller<Student>, ManageService.Caller {

    public interface ReadCaller extends ReadService.Caller<StudentTeam> {
    }

    public interface ManageCaller extends ManageService.Caller {
    }

    private ReadCaller readCaller;
    private ManageCaller manageCaller;
    private ReadService<Student> readServiceStudents;
    private ReadService<StudentTeam> readServiceStudentTeams;
    private ManageService<Student> manageServiceStudents;
    private ManageService<StudentTeam> manageServiceStudentTeams;
    private StudentTeam studentTeam;

    public StudentsService(ReadCaller caller) {
        this.readCaller = caller;
        readServiceStudents = new ReadService<>(this, WSResources.URL_WS_STUDENTS, Student.class);
        readServiceStudentTeams = new ReadService<>(caller, WSResources.URL_WS_STUDENTTEAMS, StudentTeam.class);
    }

    public StudentsService(ManageCaller caller) {
        this.manageCaller = caller;
        manageServiceStudents = new ManageService<>(this, WSResources.URL_WS_STUDENTS);
        manageServiceStudentTeams = new ManageService<>(caller, WSResources.URL_WS_STUDENTTEAMS);
    }

    public void read(Team team) {
        readServiceStudentTeams.clearHeaders();
        readServiceStudentTeams.clearParameters();
        readServiceStudentTeams.putHeaders(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        readServiceStudentTeams.putParameters(EnumParameter.TEAM.getName(), new String[]{team.getNumber()});
        readServiceStudentTeams.read();
    }

    public void create(Student student, Team team) {
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

    @Override
    public void onWaiting() {
        if (readCaller != null && manageCaller == null) {
            readCaller.onWaiting();
        }

        if (readCaller == null && manageCaller != null) {
            manageCaller.onWaiting();
        }
    }

    @Override
    public void onReaded(List<Student> students) {
    }

    @Override
    public void onCreated() {
        manageServiceStudentTeams.create(studentTeam);
    }

    @Override
    public void onUpdated() {
        manageCaller.onUpdated();
    }

    @Override
    public void onDeleted() {
        manageCaller.onDeleted();
    }

    @Override
    public void onErrors(List<String> errors) {
        if (readCaller != null && manageCaller == null) {
            readCaller.onErrors(errors);
        }

        if (readCaller == null && manageCaller != null) {
            manageCaller.onErrors(errors);
        }
    }
}

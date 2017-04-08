package com.sasd13.proadmin.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.service.IStudentService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class StudentService implements IStudentService {

    @Override
    public ServiceResult<List<StudentTeam>> read(Map<String, String[]> parameters) {
        Promise promise = new Promise("GET", WSResources.URL_WS_STUDENTTEAMS, StudentTeam.class);

        promise.setParameters(parameters);

        List<StudentTeam> results = (List<StudentTeam>) promise.execute();

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                results
        );
    }

    @Override
    public ServiceResult<List<Student>> readStudents(Map<String, String[]> parameters) {
        Promise promise = new Promise("GET", WSResources.URL_WS_STUDENTS, Student.class);

        promise.setParameters(parameters);

        List<Student> results = (List<Student>) promise.execute();

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                results
        );
    }

    @Override
    public ServiceResult<Void> create(Student student) {
        Promise promise = new Promise("POST", WSResources.URL_WS_STUDENTS);

        promise.execute(student);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> create(StudentTeam studentTeam) {
        Promise promise = new Promise("POST", WSResources.URL_WS_STUDENTTEAMS);

        promise.execute(studentTeam);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> update(StudentUpdateWrapper studentUpdateWrapper) {
        Promise promise = new Promise("PUT", WSResources.URL_WS_STUDENTS);

        promise.execute(studentUpdateWrapper);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> delete(StudentTeam[] studentTeams) {
        Promise promise = new Promise("DELETE", WSResources.URL_WS_STUDENTTEAMS);

        promise.execute(studentTeams);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                null
        );
    }
}

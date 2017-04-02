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

    private Promise promiseRead, promiseReadStudent, promiseCreateStudent, promiseCreateStudentTeam, promiseUpdate, promiseDelete;

    @Override
    public ServiceResult<List<StudentTeam>> read(Map<String, String[]> parameters) {
        if (promiseRead == null) {
            promiseRead = new Promise("GET", WSResources.URL_WS_STUDENTTEAMS, StudentTeam.class);
        }

        promiseRead.setParameters(parameters);

        List<StudentTeam> results = (List<StudentTeam>) promiseRead.execute();

        return new ServiceResult<>(
                promiseRead.isSuccess(),
                promiseRead.getResponseCode(),
                results
        );
    }

    @Override
    public ServiceResult<List<Student>> readStudents(Map<String, String[]> parameters) {
        if (promiseReadStudent == null) {
            promiseReadStudent = new Promise("GET", WSResources.URL_WS_STUDENTS, Student.class);
        }

        promiseReadStudent.setParameters(parameters);

        List<Student> results = (List<Student>) promiseReadStudent.execute();

        return new ServiceResult<>(
                promiseReadStudent.isSuccess(),
                promiseReadStudent.getResponseCode(),
                results
        );
    }

    @Override
    public ServiceResult<Void> create(Student student) {
        if (promiseCreateStudent == null) {
            promiseCreateStudent = new Promise("POST", WSResources.URL_WS_STUDENTS);
        }

        promiseCreateStudent.execute(student);

        return new ServiceResult<>(
                promiseCreateStudent.isSuccess(),
                promiseCreateStudent.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> create(StudentTeam studentTeam) {
        if (promiseCreateStudentTeam == null) {
            promiseCreateStudentTeam = new Promise("POST", WSResources.URL_WS_STUDENTTEAMS);
        }

        promiseCreateStudentTeam.execute(studentTeam);

        return new ServiceResult<>(
                promiseCreateStudentTeam.isSuccess(),
                promiseCreateStudentTeam.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> update(StudentUpdateWrapper studentUpdateWrapper) {
        if (promiseUpdate == null) {
            promiseUpdate = new Promise("PUT", WSResources.URL_WS_STUDENTS);
        }

        promiseUpdate.execute(studentUpdateWrapper);

        return new ServiceResult<>(
                promiseUpdate.isSuccess(),
                promiseUpdate.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> delete(StudentTeam[] studentTeams) {
        if (promiseDelete == null) {
            promiseDelete = new Promise("DELETE", WSResources.URL_WS_STUDENTTEAMS);
        }

        promiseDelete.execute(studentTeams);

        return new ServiceResult<>(
                promiseDelete.isSuccess(),
                promiseDelete.getResponseCode(),
                null
        );
    }
}

package com.sasd13.proadmin.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.service.IStudentService;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class StudentService implements IStudentService {

    private Promise promiseRead, promiseCreateStudent, promiseCreateStudentTeam, promiseUpdate, promiseDelete;

    @Override
    public List<Student> read(Map<String, String[]> parameters) {
        if (promiseRead == null) {
            promiseRead = new Promise("GET", WSResources.URL_WS_STUDENTTEAMS, StudentTeam.class);
        }

        promiseRead.setParameters(parameters);

        return (List<Student>) promiseRead.execute();
    }

    @Override
    public void create(Student student) {
        if (promiseCreateStudent == null) {
            promiseCreateStudent = new Promise("POST", WSResources.URL_WS_STUDENTS);
        }

        promiseCreateStudent.execute(student);
    }

    @Override
    public void create(StudentTeam studentTeam) {
        if (promiseCreateStudentTeam == null) {
            promiseCreateStudentTeam = new Promise("POST", WSResources.URL_WS_STUDENTTEAMS);
        }

        promiseCreateStudentTeam.execute(studentTeam);
    }

    @Override
    public void update(StudentUpdateWrapper studentUpdateWrapper) {
        if (promiseUpdate == null) {
            promiseUpdate = new Promise("PUT", WSResources.URL_WS_STUDENTS);
        }

        promiseUpdate.execute(studentUpdateWrapper);
    }

    @Override
    public void delete(StudentTeam studentTeam) {
        if (promiseDelete == null) {
            promiseDelete = new Promise("DELETE", WSResources.URL_WS_STUDENTTEAMS);
        }

        promiseDelete.execute(studentTeam);
    }
}

package com.sasd13.proadmin.service;

import com.sasd13.androidex.net.ICallback;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class StudentService implements ICallback {

    private static final int TASKTYPE_STUDENT = 0;
    private static final int TASKTYPE_STUDENTTEAM = 1;

    private Promise promiseRead, promiseCreateStudent, promiseCreateStudentTeam, promiseUpdate, promiseDelete;
    private Map<String, String[]> parameters;
    private StudentTeam studentTeam;
    private int taskType;
    private ICallback callback;

    public StudentService() {
        promiseRead = new Promise("GET", WSResources.URL_WS_STUDENTTEAMS, StudentTeam.class);
        promiseCreateStudent = new Promise("POST", WSResources.URL_WS_STUDENTS);
        promiseCreateStudentTeam = new Promise("POST", WSResources.URL_WS_STUDENTTEAMS);
        promiseUpdate = new Promise("PUT", WSResources.URL_WS_STUDENTS);
        promiseDelete = new Promise("DELETE", WSResources.URL_WS_STUDENTS);
        parameters = new HashMap<>();

        promiseRead.setParameters(parameters);
    }

    public void readByTeam(ICallback callback, String teamNumber) {
        parameters.clear();
        parameters.put(EnumParameter.TEAM.getName(), new String[]{teamNumber});
        promiseRead.registerCallback(callback);
        promiseRead.execute();
    }

    public void create(ICallback callback, Student student, Team team) {
        taskType = TASKTYPE_STUDENT;
        this.callback = callback;
        studentTeam = new StudentTeam();

        studentTeam.setStudent(student);
        studentTeam.setTeam(team);

        promiseCreateStudentTeam.registerCallback(this);
        promiseCreateStudent.registerCallback(this);
        promiseCreateStudent.execute(student);
    }

    public void update(ICallback callback, Student student, Student studentToUpdate) {
        promiseUpdate.registerCallback(callback);
        promiseUpdate.execute(getUpdateWrapper(student, studentToUpdate));
    }

    private StudentUpdateWrapper getUpdateWrapper(Student student, Student studentToUpdate) {
        StudentUpdateWrapper updateWrapper = new StudentUpdateWrapper();

        updateWrapper.setWrapped(student);
        updateWrapper.setNumber(studentToUpdate.getNumber());

        return updateWrapper;
    }

    public void delete(ICallback callback, StudentTeam studentTeam) {
        promiseDelete.registerCallback(callback);
        promiseDelete.execute(studentTeam);
    }

    @Override
    public void onPreExecute() {
        if (taskType == TASKTYPE_STUDENT) {
            callback.onPreExecute();
        }
    }

    @Override
    public void onPostExecute(int type, boolean success, Object result) {
        if (taskType == TASKTYPE_STUDENT) {
            if (success) {
                taskType = TASKTYPE_STUDENTTEAM;

                promiseCreateStudentTeam.execute(studentTeam);
            } else {
                callback.onPostExecute(type, false, result);
            }
        } else {
            callback.onPostExecute(type, success, result);
        }
    }

    @Override
    public void onCancelled() {
        callback.onCancelled();
    }
}

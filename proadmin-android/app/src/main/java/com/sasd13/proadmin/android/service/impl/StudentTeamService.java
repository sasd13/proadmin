package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.service.IStudentTeamService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.util.Resources;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class StudentTeamService implements IStudentTeamService {

    @Override
    public ServiceResult<List<StudentTeam>> read(Map<String, String[]> parameters) {
        Promise promise = new Promise("GET", Resources.URL_WS_STUDENTTEAMS, StudentTeam.class);

        promise.setParameters(parameters);

        List<StudentTeam> results = (List<StudentTeam>) promise.execute();

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                results
        );
    }

    @Override
    public ServiceResult<Void> create(StudentTeam studentTeam) {
        Promise promise = new Promise("POST", Resources.URL_WS_STUDENTTEAMS);

        promise.execute(new StudentTeam[]{studentTeam});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                null
        );
    }

    @Override
    public ServiceResult<Void> delete(List<StudentTeam> studentTeams) {
        Promise promise = new Promise("DELETE", Resources.URL_WS_STUDENTTEAMS);

        promise.execute(studentTeams);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                null
        );
    }
}

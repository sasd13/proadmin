package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.model.StudentTeam;
import com.sasd13.proadmin.android.service.IStudentTeamService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.AppProperties;
import com.sasd13.proadmin.android.util.Names;
import com.sasd13.proadmin.android.util.adapter.itf.StudentTeamITFAdapter;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamRequestBean;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamResponseBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class StudentTeamService implements IStudentTeamService {

    private static final String URL_WS2_STUDENTTEAMS = AppProperties.getProperty(Names.URL_WS2_STUDENTTEAMS);

    private StudentTeamITFAdapter adapter;

    public StudentTeamService() {
        adapter = new StudentTeamITFAdapter();
    }

    @Override
    public ServiceResult<List<StudentTeam>> read(Map<String, Object> criterias) {
        Promise promise = new Promise("POST", URL_WS2_STUDENTTEAMS + "/search", StudentTeamResponseBean.class);
        SearchBean searchBean = new SearchBean();

        searchBean.setCriterias(criterias);

        StudentTeamResponseBean responseBean = (StudentTeamResponseBean) promise.execute(searchBean);
        Map<String, String> errors = Collections.emptyMap();
        List<StudentTeam> list = new ArrayList<>();

        if (promise.isSuccess()) {
            errors = responseBean.getErrors();

            if (errors.isEmpty()) {
                list = adapter.adaptI2M(responseBean.getData());
            }
        }

        return new ServiceResult<>(
                promise.isSuccess() && errors.isEmpty(),
                promise.getResponseCode(),
                errors,
                list
        );
    }

    @Override
    public ServiceResult<Void> create(StudentTeam studentTeam) {
        Promise promise = new Promise("POST", URL_WS2_STUDENTTEAMS + "/create");
        StudentTeamRequestBean requestBean = new StudentTeamRequestBean();

        requestBean.setData(Arrays.asList(adapter.adaptM2I(studentTeam)));
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> delete(List<StudentTeam> studentTeams) {
        Promise promise = new Promise("POST", URL_WS2_STUDENTTEAMS + "/delete");
        StudentTeamRequestBean requestBean = new StudentTeamRequestBean();

        requestBean.setData(adapter.adaptM2I(studentTeams));
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }
}

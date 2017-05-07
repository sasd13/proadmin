package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.service.IStudentTeamService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.adapter.bean2itf.StudentTeamAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.itf2bean.StudentTeamAdapterI2B;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamRequestBean;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamResponseBean;
import com.sasd13.proadmin.util.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class StudentTeamService implements IStudentTeamService {

    @Override
    public ServiceResult<List<StudentTeam>> read(Map<String, Object> criterias) {
        Promise promise = new Promise("POST", Resources.URL_BACKEND_STUDENTTEAMS + "/search", StudentTeamResponseBean.class);

        SearchBean searchBean = new SearchBean();
        searchBean.setCriterias(criterias);

        StudentTeamResponseBean responseBean = (StudentTeamResponseBean) promise.execute(searchBean);
        List<StudentTeam> list = new ArrayList<>();

        if (promise.isSuccess()) {
            StudentTeamAdapterI2B adapter = new StudentTeamAdapterI2B();

            for (StudentTeamBean studentTeamBean : responseBean.getData()) {
                list.add(adapter.adapt(studentTeamBean));
            }
        }

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                responseBean.getErrors(),
                list
        );
    }

    @Override
    public ServiceResult<Void> create(StudentTeam studentTeam) {
        Promise promise = new Promise("POST", Resources.URL_BACKEND_STUDENTTEAMS + "/create");

        StudentTeamRequestBean requestBean = new StudentTeamRequestBean();
        List<StudentTeamBean> list = new ArrayList<>();

        list.add(new StudentTeamAdapterB2I().adapt(studentTeam));
        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> delete(List<StudentTeam> studentTeams) {
        Promise promise = new Promise("POST", Resources.URL_BACKEND_STUDENTTEAMS + "/delete");

        StudentTeamRequestBean requestBean = new StudentTeamRequestBean();
        List<StudentTeamBean> list = new ArrayList<>();
        StudentTeamAdapterB2I adapter = new StudentTeamAdapterB2I();

        for (StudentTeam studentTeam : studentTeams) {
            list.add(adapter.adapt(studentTeam));
        }

        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }
}

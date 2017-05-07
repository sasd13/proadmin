package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.service.IProjectService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.adapter.itf2bean.ProjectAdapterI2B;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.project.ProjectBean;
import com.sasd13.proadmin.itf.bean.project.ProjectResponseBean;
import com.sasd13.proadmin.util.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class ProjectService implements IProjectService {

    @Override
    public ServiceResult<List<Project>> read(Map<String, Object> criterias) {
        Promise promise = new Promise("POST", Resources.URL_BACKEND_PROJECTS + "/search", ProjectResponseBean.class);

        SearchBean searchBean = new SearchBean();
        searchBean.setCriterias(criterias);

        ProjectResponseBean responseBean = (ProjectResponseBean) promise.execute(searchBean);
        List<Project> list = new ArrayList<>();

        if (promise.isSuccess()) {
            ProjectAdapterI2B adapter = new ProjectAdapterI2B();

            for (ProjectBean projectBean : responseBean.getData()) {
                list.add(adapter.adapt(projectBean));
            }
        }

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                responseBean.getErrors(),
                list
        );
    }
}

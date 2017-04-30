package com.sasd13.proadmin.android.service.v2.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.service.v2.IProjectService;
import com.sasd13.proadmin.android.util.adapter.itf2bean.v2.ProjectAdapterI2B;
import com.sasd13.proadmin.itf.bean.project.ProjectBean;
import com.sasd13.proadmin.itf.bean.project.ProjectResponseBean;
import com.sasd13.proadmin.util.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class ProjectService implements IProjectService {

    @Override
    public ServiceResult<List<Project>> readAll() {
        Promise promise = new Promise("GET", Resources.URL_BACKEND_PROJECTS, ProjectResponseBean.class);

        ProjectResponseBean responseBean = (ProjectResponseBean) promise.execute();

        List<Project> list = new ArrayList<>();
        ProjectAdapterI2B adapter = new ProjectAdapterI2B();

        for (ProjectBean projectBean : responseBean.getData()) {
            list.add(adapter.adapt(projectBean));
        }

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                responseBean != null ? responseBean.getErrors() : Collections.<String, String>emptyMap(),
                promise.isSuccess() ? list : Collections.<Project>emptyList()
        );
    }
}

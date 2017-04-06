package com.sasd13.proadmin.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.service.IProjectService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class ProjectService implements IProjectService {

    private Promise promiseRead;

    @Override
    public ServiceResult<List<Project>> readAll() {
        if (promiseRead == null) {
            promiseRead = new Promise("GET", WSResources.URL_WS_PROJECTS, Project.class);
        }

        List<Project> results = (List<Project>) promiseRead.execute();

        return new ServiceResult<>(
                promiseRead.isSuccess(),
                promiseRead.getResponseCode(),
                results
        );
    }
}
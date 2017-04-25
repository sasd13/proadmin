package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.service.IProjectService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.util.Resources;

import java.util.Collections;
import java.util.List;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class ProjectService implements IProjectService {

    @Override
    public ServiceResult<List<Project>> readAll() {
        Promise promise = new Promise("GET", Resources.URL_WS_PROJECTS, Project.class);

        List<Project> results = (List<Project>) promise.execute();

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                results
        );
    }
}

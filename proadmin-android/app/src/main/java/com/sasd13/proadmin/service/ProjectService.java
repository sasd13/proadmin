package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.rest.promise.ReadPromise;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class ProjectService {

    private ReadPromise<Project> readPromise;

    public ProjectService(ReadPromise.Callback<Project> callback) {
        readPromise = new ReadPromise<>(callback, WSResources.URL_WS_PROJECTS, Project.class);
    }

    public void readAll() {
        readPromise.read();
    }
}

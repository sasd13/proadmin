package com.sasd13.proadmin.service;

import com.sasd13.androidex.net.ICallback;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class ProjectService {

    private Promise promiseRead;

    public ProjectService() {
        promiseRead = new Promise("GET", WSResources.URL_WS_PROJECTS, Project.class);
    }

    public void readAll(ICallback callback) {
        promiseRead.registerCallback(callback);
        promiseRead.execute();
    }
}

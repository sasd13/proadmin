package com.sasd13.proadmin.service.ws;

import com.sasd13.androidex.ws.rest.service.ReadService;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class ProjectService {

    private ReadService<Project> readService;

    public ProjectService(ReadService.Caller<Project> caller) {
        readService = new ReadService<>(caller, WSResources.URL_WS_PROJECTS, Project.class);
    }

    public void readAll() {
        readService.read();
    }
}

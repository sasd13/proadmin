package com.sasd13.proadmin.controller.project;

import com.sasd13.androidex.util.requestor.ReadRequestorStrategy;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.service.IProjectService;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ProjectReadStrategy extends ReadRequestorStrategy<Void, List<Project>> {

    private ProjectController controller;
    private IProjectService service;

    public ProjectReadStrategy(ProjectController controller, IProjectService service) {
        super();
        
        this.controller = controller;
        this.service = service;
    }

    @Override
    public List<Project> doInBackgroung(Void[] in) {
        return service.readAll();
    }

    @Override
    public void onPostExecute(List<Project> out) {
        super.onPostExecute(out);

        controller.onReadProjects(out);
    }

    @Override
    public void onCancelled(List<Project> out) {
        super.onCancelled(out);

        //controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}

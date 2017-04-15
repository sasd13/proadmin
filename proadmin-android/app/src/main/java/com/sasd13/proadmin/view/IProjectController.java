package com.sasd13.proadmin.view;

import com.sasd13.proadmin.bean.project.Project;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public interface IProjectController extends IController, IBrowsable {

    void actionLoadProjects();

    void actionShowProject(Project project);
}

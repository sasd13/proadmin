package com.sasd13.proadmin.android.view;

import com.sasd13.proadmin.android.bean.Project;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public interface IProjectController extends IController, IBrowsable {

    void actionReadProjects();

    void actionShowProject(Project project);
}

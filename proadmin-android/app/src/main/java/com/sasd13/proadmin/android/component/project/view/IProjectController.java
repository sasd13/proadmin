package com.sasd13.proadmin.android.component.project.view;

import com.sasd13.proadmin.android.model.Project;
import com.sasd13.proadmin.android.util.browser.IBrowsable;
import com.sasd13.proadmin.android.component.IController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public interface IProjectController extends IController, IBrowsable {

    void actionReadProjects();

    void actionShowProject(Project project);
}

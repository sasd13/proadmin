package com.sasd13.proadmin.android.util.adapter.itf;

import com.sasd13.proadmin.android.model.Project;
import com.sasd13.proadmin.android.util.adapter.itf.itf2model.ProjectAdapterI2M;
import com.sasd13.proadmin.itf.bean.project.ProjectBean;

/**
 * Created by ssaidali2 on 07/01/2018.
 */

public class ProjectITFAdapter extends ITFAdapter<Project, ProjectBean> {

    public ProjectITFAdapter() {
        super(new ProjectAdapterI2M());
    }
}

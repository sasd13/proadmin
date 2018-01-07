package com.sasd13.proadmin.backend.util.adapter.itf;

import com.sasd13.proadmin.backend.model.Project;
import com.sasd13.proadmin.backend.util.adapter.itf.itf2model.ProjectAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.itf.model2itf.ProjectAdapterM2I;
import com.sasd13.proadmin.itf.bean.project.ProjectBean;

public class ProjectITFAdapter extends ITFAdapter<Project, ProjectBean> {

	public ProjectITFAdapter() {
		super(new ProjectAdapterI2M(), new ProjectAdapterM2I());
	}
}

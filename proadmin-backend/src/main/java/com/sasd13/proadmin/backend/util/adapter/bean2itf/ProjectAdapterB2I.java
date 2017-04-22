package com.sasd13.proadmin.backend.util.adapter.bean2itf;

import java.text.SimpleDateFormat;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Project;
import com.sasd13.proadmin.backend.util.Constants;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.project.CoreInfo;
import com.sasd13.proadmin.itf.bean.project.ProjectBean;

public class ProjectAdapterB2I implements IAdapter<Project, ProjectBean> {

	@Override
	public ProjectBean adapt(Project s) {
		ProjectBean t = new ProjectBean();

		Id id = new Id();
		id.setId(String.valueOf(s.getId()));
		t.setId(id);

		CoreInfo coreInfo = new CoreInfo();
		coreInfo.setCode(s.getCode());
		coreInfo.setDateCreation(new SimpleDateFormat(Constants.PATTERN_DATE).format(s.getDateCreation()));
		coreInfo.setTitle(s.getTitle());
		coreInfo.setDescription(s.getDescription());
		t.setCoreInfo(coreInfo);

		return t;
	}
}

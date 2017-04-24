package com.sasd13.proadmin.ws.util.adapter.itf2bean.update;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.project.ProjectBean;
import com.sasd13.proadmin.ws.bean.Project;
import com.sasd13.proadmin.ws.bean.update.ProjectUpdate;
import com.sasd13.proadmin.ws.util.Constants;

public class ProjectUpdateAdapterI2B implements IAdapter<ProjectBean, ProjectUpdate> {

	private static final Logger LOGGER = Logger.getLogger(ProjectUpdateAdapterI2B.class);

	@Override
	public ProjectUpdate adapt(ProjectBean s) {
		ProjectUpdate t = new ProjectUpdate();

		t.setCode(s.getId().getId());

		Project project = new Project();

		try {
			project.setDateCreation(new SimpleDateFormat(Constants.PATTERN_DATE_FORMAT).parse(s.getCoreInfo().getDateCreation()));
		} catch (ParseException e) {
			LOGGER.error(e);
			throw new RuntimeException("ProjectAdapterI2B : error parsing date " + s.getCoreInfo().getDateCreation());
		}

		project.setCode(s.getCoreInfo().getCode());
		project.setTitle(s.getCoreInfo().getTitle());
		project.setDescription(s.getCoreInfo().getDescription());
		t.setWrapped(project);

		return t;
	}
}
